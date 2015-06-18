package org.renci.hearsay.dao.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.clinvar.MeasureSetType;
import org.renci.clinvar.MeasureSetType.Measure;
import org.renci.clinvar.MeasureSetType.Measure.AttributeSet;
import org.renci.clinvar.MeasureSetType.Measure.AttributeSet.Attribute;
import org.renci.clinvar.PublicSetType;
import org.renci.clinvar.ReferenceAssertionType;
import org.renci.clinvar.ReleaseType;
import org.renci.clinvar.SetElementSetType;
import org.renci.clinvar.XrefType;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.dao.model.ComplexityType;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.MoleculeType;
import org.renci.hearsay.dao.model.ReferenceCoordinate;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.SimpleAllele;
import org.renci.hearsay.dao.model.SimpleAlleleType;

public class PullClinVarTest extends AbstractPullTest {

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

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);
        hearsayDAOBean.setReferenceSequenceDAO(referenceSequenceDAO);

    }

    @Test
    public void test() {

        // File clinvarDownload = download("/pub/clinvar/xml", "ClinVarFullRelease_00-latest.xml.gz");
        File clinvarDownload = new File("/tmp", "ClinVarFullRelease_00-latest.xml.gz");
        try {
            JAXBContext jc = JAXBContext.newInstance(ReleaseType.class);
            Unmarshaller u = jc.createUnmarshaller();
            ReleaseType releaseType = (ReleaseType) u.unmarshal(new GZIPInputStream(
                    new FileInputStream(clinvarDownload)));
            int count = 0;
            List<PublicSetType> publicSetType = releaseType.getClinVarSet();
            for (PublicSetType pst : publicSetType) {
                ReferenceAssertionType rat = pst.getReferenceClinVarAssertion();
                ReferenceAssertionType.ClinVarAccession clinVarAccession = rat.getClinVarAccession();

                CanonicalAllele canonicalAllele = new CanonicalAllele();
                canonicalAllele.setActive("current".equals(rat.getRecordStatus()));
                canonicalAllele.setVersion(clinVarAccession.getVersion().toString());

                MeasureSetType mst = rat.getMeasureSet();
                List<SetElementSetType> measureSetTypeNameList = mst.getName();
                for (SetElementSetType mstNameList : measureSetTypeNameList) {
                    if ("Preferred".equals(mstNameList.getElementValue().getType())) {
                        canonicalAllele.setName(mstNameList.getElementValue().getValue());
                    }
                }

                ComplexityType complexityType = ComplexityType.SIMPLE;
                if (mst.getMeasure().size() > 1) {
                    complexityType = ComplexityType.COMPLEX;
                }
                canonicalAllele.setComplexityType(complexityType);

                // em.getTransaction().begin();
                // canonicalAllele.setId(hearsayDAOBean.getCanonicalAlleleDAO().save(canonicalAllele));
                // em.getTransaction().commit();

                if ("Variant".equals(mst.getType())) {

                    Identifier identifier = new Identifier();
                    identifier.setSystem("http://www.ncbi.nlm.nih.gov/clinvar");
                    identifier.setValue(mst.getID().toString());
                    // em.getTransaction().begin();
                    // identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                    // em.getTransaction().commit();
                    // System.out.println(identifier.toString());

                    canonicalAllele.getRelatedIdentifiers().add(identifier);
                }

                for (MoleculeType mType : MoleculeType.values()) {
                    if (mType.getPrefixes().contains(canonicalAllele.getName().substring(0, 3))) {
                        canonicalAllele.setMoleculeType(mType);
                        break;
                    }
                }

                for (Measure measure : mst.getMeasure()) {

                    List<AttributeSet> attributeSetList = measure.getAttributeSet();

                    Set<SimpleAllele> simpleAlleleSet = new HashSet<SimpleAllele>();

                    for (AttributeSet attributeSet : attributeSetList) {
                        Attribute attribute = attributeSet.getAttribute();
                        for (SimpleAlleleType saType : SimpleAlleleType.values()) {
                            if (saType.getType().equals(attribute.getType())) {
                                SimpleAllele simpleAllele = new SimpleAllele();
                                simpleAllele.setName(attribute.getValue());
                                if (saType.equals(SimpleAlleleType.TRANSCRIPT)) {
                                    simpleAllele.setAllele(attribute.getValue().substring(
                                            attribute.getValue().length() - 1, attribute.getValue().length()));
                                }
                                simpleAllele.setType(saType);
                                simpleAlleleSet.add(simpleAllele);
                                break;
                            }
                        }
                    }

                    for (AttributeSet attributeSet : attributeSetList) {
                        Attribute attribute = attributeSet.getAttribute();
                        if ("MolecularConsequence".equals(attribute.getType())) {
                            String sequenceOntologyId = null;
                            String refSeqId = null;
                            for (XrefType xref : attributeSet.getXRef()) {
                                if ("Sequence Ontology".equals(xref.getDB())) {
                                    sequenceOntologyId = xref.getID();
                                }
                                if ("RefSeq".equals(xref.getDB())) {
                                    refSeqId = xref.getID();
                                }
                            }

                            if (StringUtils.isNotEmpty(refSeqId) && StringUtils.isNotBlank(sequenceOntologyId)) {
                                for (SimpleAllele sa : simpleAlleleSet) {
                                    if (sa.getName().startsWith(refSeqId.substring(0, refSeqId.indexOf(".")))) {
                                        sa.setPrimaryChangeSOId(Integer.valueOf(sequenceOntologyId.replace("SO:", "")));
                                    }
                                }
                            }
                        }
                    }

                    for (SimpleAllele sa : simpleAlleleSet) {
                        String referenceSequenceAccession = sa.getName().substring(0, sa.getName().indexOf("."));
                        ReferenceSequence referenceSequence = null;
                        try {
                            List<ReferenceSequence> potentialRefSeqList = hearsayDAOBean.getReferenceSequenceDAO()
                                    .findByIdentifierValue(referenceSequenceAccession);
                            if (potentialRefSeqList != null && !potentialRefSeqList.isEmpty()) {
                                referenceSequence = potentialRefSeqList.get(0);
                            }
                        } catch (HearsayDAOException e) {
                            e.printStackTrace();
                        }

                        ReferenceCoordinate referenceCoordinate = new ReferenceCoordinate();
                        referenceCoordinate.setReferenceSequence(referenceSequence);
                        sa.setReferenceCoordinate(referenceCoordinate);
                    }

                    canonicalAllele.getRelatedSimpleAlleles().addAll(simpleAlleleSet);

                }

                JAXBContext canonicalAlleleJAXBContext = JAXBContext.newInstance(CanonicalAllele.class);
                Marshaller canonicalAlleleMarshaller = canonicalAlleleJAXBContext.createMarshaller();
                canonicalAlleleMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                FileWriter fw = new FileWriter(new File("/tmp", String.format("%s.xml", clinVarAccession.getAcc())));
                canonicalAlleleMarshaller.marshal(canonicalAllele, fw);

                if (count == 50) {
                    break;
                }
                count++;

                // break;

                // List<SetElementSetType> sestList = mst.getName();
                // for (SetElementSetType sest : sestList) {
                // // preferred?
                // String measureNameType = sest.getElementValue().getType();
                // String measureNameValue = sest.getElementValue().getValue();
                // }
                // List<Measure> measureList = mst.getMeasure();
                // for (Measure measure : measureList) {
                // List<XrefType> xrefTypeList = measure.getXRef();
                // for (XrefType xrefType : xrefTypeList) {
                //
                // if ("Gene".equals(xrefType.getDB())) {
                // List<Gene> foundGeneList = hearsayDAOBean.getGeneDAO().findByIdentifierValue(
                // xrefType.getID());
                // if (foundGeneList != null && !foundGeneList.isEmpty()) {
                // Gene gene = foundGeneList.get(0);
                //
                // }
                //
                // }
                // }
                // }
            }

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
