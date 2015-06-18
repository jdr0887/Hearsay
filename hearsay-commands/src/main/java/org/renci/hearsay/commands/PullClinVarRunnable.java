package org.renci.hearsay.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.karaf.shell.commands.Command;
import org.renci.clinvar.MeasureSetType;
import org.renci.clinvar.MeasureSetType.Measure;
import org.renci.clinvar.PublicSetType;
import org.renci.clinvar.ReferenceAssertionType;
import org.renci.clinvar.ReleaseType;
import org.renci.clinvar.SetElementSetType;
import org.renci.clinvar.XrefType;
import org.renci.hearsay.commands.util.NCBIFTPUtil;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "pull-clinvar", description = "Pull ClinVar")
public class PullClinVarRunnable implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(PullClinVarRunnable.class);

    private HearsayDAOBean hearsayDAOBean;

    public PullClinVarRunnable() {
        super();
    }

    @Override
    public void run() {

        File clinvarDownload = NCBIFTPUtil.download("/pub/clinvar/xml", "ClinVarFullRelease_00-latest.xml.gz");

        try {
            JAXBContext jc = JAXBContext.newInstance(ReleaseType.class);
            Unmarshaller u = jc.createUnmarshaller();
            ReleaseType releaseType = (ReleaseType) u.unmarshal(new GZIPInputStream(
                    new FileInputStream(clinvarDownload)));

            List<PublicSetType> publicSetType = releaseType.getClinVarSet();
            for (PublicSetType pst : publicSetType) {
                ReferenceAssertionType rat = pst.getReferenceClinVarAssertion();
                ReferenceAssertionType.ClinVarAccession clinVarAccession = rat.getClinVarAccession();

                CanonicalAllele canonicalAllele = new CanonicalAllele();
                canonicalAllele.setActive("current".equals(rat.getRecordStatus()));
                canonicalAllele.setVersion(clinVarAccession.getVersion().toString());

                canonicalAllele.setId(hearsayDAOBean.getCanonicalAlleleDAO().save(canonicalAllele));

                MeasureSetType mst = rat.getMeasureSet();
                List<SetElementSetType> measureSetTypeNameList = mst.getName();
                for (SetElementSetType mstNameList : measureSetTypeNameList) {
                    if ("Preferred".equals(mstNameList.getElementValue().getType())) {
                        canonicalAllele.setName(mstNameList.getElementValue().getValue());
                    }
                }
                if ("Variant".equals(mst.getType())) {

                    Identifier identifier = new Identifier();
                    identifier.setSystem("http://www.ncbi.nlm.nih.gov/clinvar");
                    identifier.setValue(mst.getID().toString());
                    identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                    logger.info(identifier.toString());

                    canonicalAllele.getRelatedIdentifiers().add(identifier);
                }

                List<SetElementSetType> sestList = mst.getName();
                for (SetElementSetType sest : sestList) {
                    String measureNameType = sest.getElementValue().getType();
                    String measureNameValue = sest.getElementValue().getValue();
                }

                List<Measure> measureList = mst.getMeasure();
                for (Measure measure : measureList) {
                    if ("single nucleotide variant".equals(measure.getType())) {
                        // SimpleAllele simpleAllele = new SimpleAllele();
                    }
                    List<XrefType> xrefTypeList = measure.getXRef();
                    for (XrefType xrefType : xrefTypeList) {

                        if ("Gene".equals(xrefType.getDB())) {
                            List<Gene> foundGeneList = hearsayDAOBean.getGeneDAO().findByIdentifierValue(
                                    xrefType.getID());
                            if (foundGeneList != null && !foundGeneList.isEmpty()) {
                                Gene gene = foundGeneList.get(0);

                            }

                        }
                    }
                }
            }

        } catch (JAXBException | IOException | HearsayDAOException e) {
            e.printStackTrace();
        }
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
