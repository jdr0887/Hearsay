package org.renci.hearsay.dao.jpa;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.gbff.parser.GBFFManager;
import org.renci.gbff.parser.model.Sequence;
import org.renci.gbff.parser.model.Source;
import org.renci.hearsay.dao.HearsayDAOBean;

public class PullReferenceSequenceInfoTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    public PullReferenceSequenceInfoTest() {
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
    public void download() {

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

            List<FTPFile> ftpFileList = Arrays.asList(ftpClient.listFiles("/refseq/release/vertebrate_mammalian/",
                    new FTPFileFilter() {

                        @Override
                        public boolean accept(FTPFile ftpFile) {
                            if (ftpFile != null && ftpFile.getName().endsWith("rna.gbff.gz")) {
                                return true;
                            }
                            return false;
                        }
                    }));

            for (FTPFile ftpFile : ftpFileList) {
                File tmpFile = new File(System.getProperty("java.io.tmpdir", "/tmp"), ftpFile.getName());
                try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(tmpFile))) {
                    ftpClient.retrieveFile(String.format("/refseq/release/vertebrate_mammalian/%s", ftpFile.getName()),
                            fos);
                    fos.flush();
                }
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

    @Test
    public void pull() {

        List<File> outputFileList = new ArrayList<File>();

        File tmpDir = new File("/tmp");
        for (File f : tmpDir.listFiles()) {
            if (f.getName().startsWith("vertebrate_mammalian")) {
                outputFileList.add(f);
            }
        }

        Iterator<File> outputFileListIter = outputFileList.iterator();
        while (outputFileListIter.hasNext()) {
            File f = outputFileListIter.next();

            GBFFManager gbffMgr = GBFFManager.getInstance();
            List<Sequence> sequenceList = gbffMgr.deserialize(f);
            Set<String> organismSet = new HashSet<String>();
            Set<String> accessionPrefixSet = new HashSet<String>();
            for (Sequence sequence : sequenceList) {
                Source source = sequence.getSource();
                organismSet.add(source.getOrganism());
                String accessionPrefix = sequence.getAccession().substring(0, 3);
                accessionPrefixSet.add(accessionPrefix);
            }

            boolean keepIt = false;

            for (String organism : organismSet) {
                if (organism.contains("Homo sapiens")) {
                    keepIt = true;
                }
            }

            if (keepIt
                    && (accessionPrefixSet.contains("NM_") || accessionPrefixSet.contains("XM_")
                            || accessionPrefixSet.contains("NR_") || accessionPrefixSet.contains("XR_"))) {
                keepIt = true;
            }

            if (!keepIt) {
                System.out.println("removing: " + f.getName());
                outputFileListIter.remove();
                // f.delete();
            }
        }

        for (File f : outputFileList) {
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

                File tmpFile = new File(System.getProperty("java.io.tmpdir", "/tmp"), f.getName()
                        .replace("gbff", "fna"));
                try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(tmpFile))) {
                    ftpClient.retrieveFile(String.format("/refseq/release/vertebrate_mammalian/%s", f.getName()
                            .replace("gbff", "fna")), fos);
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
        }

        // for (File f : outputFileList) {
        //
        // // parse
        // try {
        //
        // GenBankParser parser = GenBankParser.getInstance();
        // List<GenBankInfo> infoList = parser.parse(f);
        // for (GenBankInfo info : infoList) {
        //
        // System.out.println(f.getName());
        //
        // ReferenceSequence referenceSequence = new ReferenceSequence();
        // for (Feature feature : info.getFeatures()) {
        //
        // if ("source".equals(feature.getType())) {
        // ReferenceSequenceChromosomeType chromosomeType = null;
        // for (ReferenceSequenceChromosomeType rsct : ReferenceSequenceChromosomeType.values()) {
        // if (rsct.getValue().equals(feature.getQualifiers().getProperty("chromosome"))) {
        // chromosomeType = rsct;
        // break;
        // }
        // }
        // referenceSequence.setChromosomeType(chromosomeType);
        // }
        //
        // if ("CDS".equals(feature.getType())) {
        // // we have a ReferenceSequence of a Transcript type
        // referenceSequence.setType(ReferenceSequenceType.TRANSCRIPT);
        // List<Gene> geneList = hearsayDAOBean.getGeneDAO().findBySymbol(
        // feature.getQualifiers().getProperty("gene"));
        // if (geneList != null && !geneList.isEmpty()) {
        // referenceSequence.setGene(geneList.get(0));
        // }
        // String location = feature.getLocation();
        // referenceSequence
        // .setCdsStart(Integer.valueOf(location.substring(0, location.indexOf(".."))));
        // referenceSequence.setCdsEnd(Integer.valueOf(location.substring(location.indexOf("..") + 2,
        // location.length())));
        // System.out.println(referenceSequence.toString());
        // }
        // }
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // // f.delete();
        // }

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
