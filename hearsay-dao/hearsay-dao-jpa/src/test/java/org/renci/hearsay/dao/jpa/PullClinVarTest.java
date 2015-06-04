package org.renci.hearsay.dao.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.clinvar.MeasureSetType;
import org.renci.clinvar.MeasureSetType.Measure;
import org.renci.clinvar.PublicSetType;
import org.renci.clinvar.ReferenceAssertionType;
import org.renci.clinvar.ReleaseType;
import org.renci.clinvar.SetElementSetType;
import org.renci.clinvar.XrefType;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Identifier;

public class PullClinVarTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    public PullClinVarTest() {
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

        CanonicalAlleleDAOImpl canonicalAlleleDAO = new CanonicalAlleleDAOImpl();
        canonicalAlleleDAO.setEntityManager(em);
        hearsayDAOBean.setCanonicalAlleleDAO(canonicalAlleleDAO);

    }

    @Test
    public void test() {
        File clinvarDownload = new File("/home/jdr0887/Downloads", "ClinVarFullRelease_2015-05.xml.gz");
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
                canonicalAllele.setName(clinVarAccession.getAcc());
                canonicalAllele.setActive("current".equals(rat.getRecordStatus()));
                canonicalAllele.setVersion(clinVarAccession.getVersion().toString());

                em.getTransaction().begin();
                canonicalAllele.setId(hearsayDAOBean.getCanonicalAlleleDAO().save(canonicalAllele));
                em.getTransaction().commit();

                MeasureSetType mst = rat.getMeasureSet();
                if ("Variant".equals(mst.getType())) {

                    Identifier identifier = new Identifier();
                    identifier
                            .setSystem("ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/xml/ClinVarFullRelease_00-latest.xml.gz");
                    identifier.setValue(mst.getID().toString());
                    em.getTransaction().begin();
                    identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                    em.getTransaction().commit();
                    System.out.println(identifier.toString());

                    canonicalAllele.getRelatedIdentifiers().add(identifier);
                }

                List<SetElementSetType> sestList = mst.getName();
                for (SetElementSetType sest : sestList) {
                    // preferred?
                    String measureNameType = sest.getElementValue().getType();
                    String measureNameValue = sest.getElementValue().getValue();
                }
                List<Measure> measureList = mst.getMeasure();
                for (Measure measure : measureList) {
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

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
