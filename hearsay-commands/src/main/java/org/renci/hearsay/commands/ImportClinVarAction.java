package org.renci.hearsay.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.renci.clinvar.PublicSetType;
import org.renci.clinvar.ReleaseType;

public class ImportClinVarAction implements Runnable {

    public ImportClinVarAction() {
        super();
    }

    @Override
    public void run() {
        File clinvarDownload = new File("/home/jdr0887/Downloads", "ClinVarFullRelease_2015-05.xml.gz");
        try {
            JAXBContext jc = JAXBContext.newInstance(ReleaseType.class);
            Unmarshaller u = jc.createUnmarshaller();
            ReleaseType releaseType = (ReleaseType) u.unmarshal(new GZIPInputStream(
                    new FileInputStream(clinvarDownload)));

            List<PublicSetType> publicSetType = releaseType.getClinVarSet();
            for (PublicSetType pst : publicSetType) {
            }

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ImportClinVarAction runnable = new ImportClinVarAction();
        runnable.run();
    }

}
