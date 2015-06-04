package org.renci.hearsay.dao.jpa;

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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.renci.hearsay.dao.model.Identifier;

public class PullNCBIGeneInfoTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    public PullNCBIGeneInfoTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);
        hearsayDAOBean.setGeneDAO(geneDAO);

        GeneSymbolDAOImpl geneSymbolDAO = new GeneSymbolDAOImpl();
        geneSymbolDAO.setEntityManager(em);
        hearsayDAOBean.setGeneSymbolDAO(geneSymbolDAO);

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

    }

    @Test
    public void pull() {
        File tmpFile = new File(System.getProperty("java.io.tmpdir", "/tmp"), "Homo_sapiens.gene_info.gz");

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
                return;
            }

            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(tmpFile))) {
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

        // parse
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(
                tmpFile))))) {

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
                    System.out.println(exampleGene.toString());
                    if (potentiallyFoundGeneList != null && !potentiallyFoundGeneList.isEmpty()) {
                        System.out.println("Gene is already persisted");
                        continue;
                    }
                    em.getTransaction().begin();
                    exampleGene.setId(hearsayDAOBean.getGeneDAO().save(exampleGene));
                    em.getTransaction().commit();

                    if (!synonyms.trim().equals("-")) {
                        StringTokenizer geneSymbolStringTokenizer = new StringTokenizer(synonyms, "|");

                        while (geneSymbolStringTokenizer.hasMoreTokens()) {
                            String geneSymbol = geneSymbolStringTokenizer.nextToken();
                            GeneSymbol gs = new GeneSymbol();
                            gs.setSymbol(geneSymbol);
                            gs.setGene(exampleGene);
                            em.getTransaction().begin();
                            gs.setId(hearsayDAOBean.getGeneSymbolDAO().save(gs));
                            em.getTransaction().commit();
                            System.out.println(geneSymbol.toString());
                            exampleGene.getAliases().add(gs);
                        }
                    }

                    Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/gene", geneId);
                    em.getTransaction().begin();
                    identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                    em.getTransaction().commit();
                    System.out.println(identifier.toString());

                    exampleGene.getIdentifiers().add(identifier);

                    em.getTransaction().begin();
                    hearsayDAOBean.getGeneDAO().save(exampleGene);
                    em.getTransaction().commit();

                }

            }

        } catch (IOException | HearsayDAOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
