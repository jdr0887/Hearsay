package org.renci.hearsay.dao.jpa;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class DownloadMappingFileRunnable implements Runnable {

    public DownloadMappingFileRunnable() {
        super();
    }

    @Override
    public void run() {

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
            File tmpFile = new File(System.getProperty("java.io.tmpdir", "/tmp"),
                    "GCF_000001405.28_knownrefseq_alignments.gff3");
            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(tmpFile))) {
                ftpClient.retrieveFile(String.format("/refseq/H_sapiens/alignments/%s", tmpFile.getName()), fos);
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

    public static void main(String[] args) {
        DownloadMappingFileRunnable runnable = new DownloadMappingFileRunnable();
        runnable.run();
    }
}
