package org.renci.hearsay.dao.jpa;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public abstract class AbstractPullTest {

    protected File download(String path, String name) {

        File ret = new File(System.getProperty("java.io.tmpdir", "/tmp"), name);
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
                return null;
            }

            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(ret))) {
                ftpClient.retrieveFile(String.format("%s/%s", path, name), fos);
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

}
