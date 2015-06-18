package org.renci.hearsay.commands;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.renci.hearsay.dao.model.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PullNCBIGenesRunnable implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(PullNCBIGenesRunnable.class);

    private HearsayDAOBean hearsayDAOBean;

    public PullNCBIGenesRunnable() {
        super();
    }

    @Override
    public void run() {
        logger.debug("ENTERING run()");

        File genesFile = download();

        // parse
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(
                genesFile))))) {

            // #Format: tax_id GeneID Symbol LocusTag Synonyms dbXrefs chromosome map_location description type_of_gene
            // Symbol_from_nomenclature_authority Full_name_from_nomenclature_authority Nomenclature_status
            // Other_designations Modification_date (tab is used as a separator, pound sign - start of a comment)
            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("#")) {
                    continue;
                }

                try (Scanner scanner = new Scanner(line).useDelimiter("\t")) {

                    String taxId = scanner.next();
                    String geneId = scanner.next();
                    String symbol = scanner.next();
                    String locusTag = scanner.next();
                    String synonyms = scanner.next();
                    String dbXrefs = scanner.next();
                    String chromosome = scanner.next();
                    String mapLocation = scanner.next();
                    String description = scanner.next();
                    // String typeOfGene = st.nextToken();
                    // String symbolFromNomenclatureAuthority = st.nextToken();
                    // String nameFromNomenclatureAuthority = st.nextToken();
                    // String nomenclatureStatus = st.nextToken();
                    // String otherDesignations = st.nextToken();
                    // String modificationDate = st.nextToken();

                    Gene exampleGene = new Gene();
                    exampleGene.setDescription(description);
                    exampleGene.setSymbol(symbol);

                    List<Gene> potentiallyFoundGeneList = hearsayDAOBean.getGeneDAO().findByExample(exampleGene);
                    logger.info(exampleGene.toString());
                    if (potentiallyFoundGeneList != null && !potentiallyFoundGeneList.isEmpty()) {
                        logger.warn("Gene is already persisted");
                        continue;
                    }
                    exampleGene.setId(hearsayDAOBean.getGeneDAO().save(exampleGene));

                    if (!synonyms.trim().equals("-")) {
                        StringTokenizer geneSymbolStringTokenizer = new StringTokenizer(synonyms, "|");

                        while (geneSymbolStringTokenizer.hasMoreTokens()) {
                            String geneSymbol = geneSymbolStringTokenizer.nextToken();
                            GeneSymbol gs = new GeneSymbol();
                            gs.setSymbol(geneSymbol);
                            gs.setGene(exampleGene);
                            gs.setId(hearsayDAOBean.getGeneSymbolDAO().save(gs));
                            // System.out.println(geneSymbol.toString());
                            exampleGene.getAliases().add(gs);
                        }
                    }

                    Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/gene", geneId);
                    identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                    logger.info(identifier.toString());
                    exampleGene.getIdentifiers().add(identifier);
                    hearsayDAOBean.getGeneDAO().save(exampleGene);

                }

            }

        } catch (IOException | HearsayDAOException e) {
            e.printStackTrace();
        }
        logger.debug("FINISHED run()");
    }

    private File download() {
        File ret = new File(System.getProperty("java.io.tmpdir", "/tmp"), "Homo_sapiens.gene_info.gz");

        FTPClient ftpClient = new FTPClient();
        // download
        try {

            ftpClient.connect("ftp.ncbi.nlm.nih.gov");

            ftpClient.login("anonymous", "anonymous");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                return null;
            }

            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(ret))) {
                ftpClient.retrieveFile("/gene/DATA/GENE_INFO/Mammalia/Homo_sapiens.gene_info.gz", fos);
                fos.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
