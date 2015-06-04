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
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.renci.hearsay.dao.model.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "pull-ncbi-gene-info", description = "Pull NCBI Gene Info")
public class PullNCBIGeneInfoAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(PullNCBIGeneInfoAction.class);

    private HearsayDAOBean hearsayDAOBean;

    public PullNCBIGeneInfoAction() {
        super();
    }

    @Override
    public Object doExecute() {
        FTPClient ftpClient = new FTPClient();
        File tmpFile = new File(System.getProperty("java.io.tmpdir", "/tmp"), "Homo_sapiens.gene_info.gz");
        try (GZIPInputStream gzipIS = new GZIPInputStream(new FileInputStream(tmpFile));
                BufferedReader br = new BufferedReader(new InputStreamReader(gzipIS))) {
            // download
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

            OutputStream fos = new BufferedOutputStream(new FileOutputStream(tmpFile));
            ftpClient.retrieveFile("/gene/DATA/GENE_INFO/Mammalia/Homo_sapiens.gene_info.gz", fos);
            fos.flush();
            fos.close();

            // #Format: tax_id GeneID Symbol LocusTag Synonyms dbXrefs chromosome map_location description type_of_gene
            // Symbol_from_nomenclature_authority Full_name_from_nomenclature_authority Nomenclature_status
            // Other_designations Modification_date (tab is used as a separator, pound sign - start of a comment)
            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("#")) {
                    continue;
                }

                StringTokenizer st = new StringTokenizer(line, "\t");

                String taxId = st.nextToken();
                String geneId = st.nextToken();
                String symbol = st.nextToken();
                String locusTag = st.nextToken();
                String synonyms = st.nextToken();
                String dbXrefs = st.nextToken();
                String chromosome = st.nextToken();
                String mapLocation = st.nextToken();
                String description = st.nextToken();
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
                if (potentiallyFoundGeneList != null && !potentiallyFoundGeneList.isEmpty()) {
                    logger.debug("Gene is already persisted: {}", exampleGene.toString());
                    continue;
                }

                exampleGene.setId(hearsayDAOBean.getGeneDAO().save(exampleGene));
                logger.info(exampleGene.toString());

                if (!synonyms.trim().equals("-")) {
                    StringTokenizer geneSymbolStringTokenizer = new StringTokenizer(synonyms, "|");

                    logger.info(synonyms);
                    while (geneSymbolStringTokenizer.hasMoreTokens()) {
                        String geneSymbol = geneSymbolStringTokenizer.nextToken();
                        GeneSymbol gs = new GeneSymbol();
                        gs.setSymbol(geneSymbol);
                        gs.setGene(exampleGene);
                        gs.setId(hearsayDAOBean.getGeneSymbolDAO().save(gs));
                        logger.info(geneSymbol.toString());
                        exampleGene.getAliases().add(gs);
                    }
                }

                Identifier identifier = new Identifier();
                identifier
                        .setSystem("ftp://ftp.ncbi.nlm.nih.gov/gene/DATA/GENE_INFO/Mammalia/Homo_sapiens.gene_info.gz");
                identifier.setValue(geneId);
                identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                logger.info(identifier.toString());

                exampleGene.getIdentifiers().add(identifier);

                hearsayDAOBean.getGeneDAO().save(exampleGene);

            }

        } catch (IOException | HearsayDAOException e) {
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
        return null;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
