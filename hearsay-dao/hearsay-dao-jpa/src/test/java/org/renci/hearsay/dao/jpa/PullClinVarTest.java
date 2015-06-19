package org.renci.hearsay.dao.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.util.ArithmeticUtils;
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
import org.renci.hearsay.dao.model.DirectionType;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.IntronOffset;
import org.renci.hearsay.dao.model.MoleculeType;
import org.renci.hearsay.dao.model.ReferenceCoordinate;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.SimpleAllele;
import org.renci.hearsay.dao.model.SimpleAlleleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PullClinVarTest extends AbstractPullTest {

    private final Logger logger = LoggerFactory.getLogger(PullClinVarTest.class);

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static final HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    private static final Pattern locationPattern = Pattern.compile("[A-Za-z]");

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
                    String dbSNPId = null;
                    for (XrefType xref : measure.getXRef()) {
                        if ("dbSNP".equals(xref.getDB())) {
                            dbSNPId = xref.getID();
                        }
                    }

                    Set<SimpleAllele> simpleAlleleSet = new HashSet<SimpleAllele>();
                    for (AttributeSet attributeSet : attributeSetList) {
                        Attribute attribute = attributeSet.getAttribute();
                        for (SimpleAlleleType saType : SimpleAlleleType.values()) {
                            if (saType.getType().equals(attribute.getType())) {
                                SimpleAllele simpleAllele = new SimpleAllele();
                                simpleAllele.setName(attribute.getValue());
                                if (saType.equals(SimpleAlleleType.TRANSCRIPT)
                                        || saType.equals(SimpleAlleleType.GENOMIC)) {
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

                        String hgvsDescription = sa.getName().substring(sa.getName().indexOf(":") + 1,
                                sa.getName().length());
                        String type = hgvsDescription.substring(0, 1);
                        if (type.equals("c") || type.equals("g")) {
                            String s = hgvsDescription.substring(2);
                            Matcher locationMatcher = locationPattern.matcher(s);
                            locationMatcher.find();
                            String location = s.substring(0, locationMatcher.start());
                            s = s.substring(locationMatcher.start());
                            if (s.contains(">")) {
                                referenceCoordinate.setRefAllele(s.substring(0, s.indexOf(">")));
                                if (NumberUtils.isNumber(location)) {
                                    // a change in the coding
                                    referenceCoordinate.setStart(Integer.valueOf(location) - 1);
                                    referenceCoordinate.setEnd(Integer.valueOf(location));
                                } else {
                                    if (location.contains("-") && !location.startsWith("-")) {
                                        // a change in the 3' end of an intron
                                        Integer start = Integer.valueOf(location.substring(0, location.indexOf("-")));
                                        Integer end = Integer.valueOf(location.substring(location.indexOf("-") + 1,
                                                location.length()));
                                        IntronOffset intron = new IntronOffset(start, end, DirectionType.MINUS);
                                        referenceCoordinate.setIntronOffset(intron);
                                    }

                                    if (location.startsWith("-")) {
                                        // a change 5' of the ATG (in the 5'UTR)
                                    }

                                    if (location.startsWith("*")) {
                                        // a change 3' of the stop codon (in the 3'UTR)
                                    }

                                    if (location.contains("+")) {
                                        // a change in the 5' end of an intron
                                        Integer end = Integer.valueOf(location.substring(location.indexOf("+") + 1,
                                                location.length()));
                                        Integer start = end - 1;
                                        IntronOffset intron = new IntronOffset(start, end, DirectionType.PLUS);
                                        referenceCoordinate.setIntronOffset(intron);
                                    }

                                }
                            }
                        }

                        if (StringUtils.isNotEmpty(dbSNPId)) {
                            Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/snp", dbSNPId);
                            try {
                                List<Identifier> possibleIdentifiers = hearsayDAOBean.getIdentifierDAO().findByExample(
                                        identifier);
                                if (possibleIdentifiers != null && !possibleIdentifiers.isEmpty()) {
                                    identifier = possibleIdentifiers.get(0);
                                } else {
                                    em.getTransaction().begin();
                                    identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                                    em.getTransaction().commit();
                                }
                            } catch (HearsayDAOException e) {
                                e.printStackTrace();
                            }
                            logger.info(identifier.toString());
                            referenceCoordinate.getIdentifiers().add(identifier);
                        }

                        sa.setReferenceCoordinate(referenceCoordinate);
                    }

                    canonicalAllele.getRelatedSimpleAlleles().addAll(simpleAlleleSet);

                }

                JAXBContext canonicalAlleleJAXBContext = JAXBContext.newInstance(CanonicalAllele.class);
                Marshaller canonicalAlleleMarshaller = canonicalAlleleJAXBContext.createMarshaller();
                canonicalAlleleMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                File dir = new File("/tmp", "clingen");
                dir.mkdirs();
                FileWriter fw = new FileWriter(new File(dir, String.format("%s.xml", clinVarAccession.getAcc())));
                canonicalAlleleMarshaller.marshal(canonicalAllele, fw);

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
