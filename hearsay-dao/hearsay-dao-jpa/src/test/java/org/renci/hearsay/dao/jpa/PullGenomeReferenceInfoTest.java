package org.renci.hearsay.dao.jpa;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

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
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.dao.model.Identifier;

public class PullGenomeReferenceInfoTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    public PullGenomeReferenceInfoTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

        GenomeReferenceDAOImpl genomeReferenceDAO = new GenomeReferenceDAOImpl();
        genomeReferenceDAO.setEntityManager(em);
        hearsayDAOBean.setGenomeReferenceDAO(genomeReferenceDAO);

    }

    @Test
    public void pull() {

        FTPClient ftpClient = new FTPClient();
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

            File refseqAssemblySummary = new File(System.getProperty("java.io.tmpdir", "/tmp"),
                    "assembly_summary_refseq.txt");
            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(refseqAssemblySummary))) {
                ftpClient.retrieveFile(String.format("/genomes/refseq/%s", refseqAssemblySummary.getName()), fos);
                fos.flush();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(refseqAssemblySummary))) {
                // # assembly_accession bioproject biosample wgs_master refseq_category taxid species_taxid
                // organism_name infraspecific_name isolate version_status assembly_level release_type genome_rep
                // seq_rel_date asm_name submitter gbrs_paired_asm paired_asm_comp ftp_path
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) {
                        continue;
                    }
                    try (Scanner scanner = new Scanner(line).useDelimiter("\t")) {
                        String assemblyAccession = scanner.next();
                        String bioProject = scanner.next();
                        String bioSample = scanner.next();
                        String wgsMaster = scanner.next();
                        String refseqCategory = scanner.next();
                        String taxId = scanner.next();
                        String speciesTaxId = scanner.next();
                        String organismName = scanner.next();
                        if (!"homo sapiens".equalsIgnoreCase(organismName)) {
                            continue;
                        }
                        String infraspecificName = scanner.next();
                        String isolate = scanner.next();
                        String versionStatus = scanner.next();
                        String assemblyLevel = scanner.next();
                        String releaseType = scanner.next();
                        String genomeRep = scanner.next();
                        String seqReleaseDate = scanner.next();
                        String asmName = scanner.next();
                        if (!asmName.startsWith("GR")) {
                            continue;
                        }
                        String submitter = scanner.next();
                        String gbrsPairedASM = scanner.next();
                        String pairedASMComp = scanner.next();
                        String ftpPath = scanner.next();

                        GenomeReference exampleGenomeReference = new GenomeReference();
                        exampleGenomeReference.setName(asmName);

                        List<GenomeReference> potentiallyFoundGenomeReferenceList = hearsayDAOBean
                                .getGenomeReferenceDAO().findByExample(exampleGenomeReference);
                        System.out.println(exampleGenomeReference.toString());
                        if (potentiallyFoundGenomeReferenceList != null
                                && !potentiallyFoundGenomeReferenceList.isEmpty()) {
                            System.out.println("GenomeReference is already persisted");
                            continue;
                        }
                        em.getTransaction().begin();
                        exampleGenomeReference.setId(hearsayDAOBean.getGenomeReferenceDAO()
                                .save(exampleGenomeReference));
                        em.getTransaction().commit();

                        String id = getGenomeReferenceAssemblyId(assemblyAccession);
                        System.out.println(id);
                        
                        Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/assembly", id);
                        em.getTransaction().begin();
                        identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                        em.getTransaction().commit();

                        exampleGenomeReference.getIdentifiers().add(identifier);

                        em.getTransaction().begin();
                        hearsayDAOBean.getGenomeReferenceDAO().save(exampleGenomeReference);
                        em.getTransaction().commit();

                    }
                }
            } catch (HearsayDAOException | IOException e) {
                e.printStackTrace();
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

    }

    private String getGenomeReferenceAssemblyId(String assemblyAccession) {
        String ret = null;
        try {
            String url = String.format("http://www.ncbi.nlm.nih.gov/assembly/%s?report=xml&format=text",
                    assemblyAccession);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("DocumentSummary")) {
                    ret = inputLine.substring(inputLine.indexOf("\"") + 1, inputLine.lastIndexOf("\""));
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
