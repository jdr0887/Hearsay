package org.renci.hearsay.dao.rs;

import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.ChromosomeDAO;
import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.IdentifierDAO;
import org.renci.hearsay.dao.IntronOffsetDAO;
import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.PopulationFrequencyDAO;
import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.RegionDAO;
import org.renci.hearsay.dao.SimpleAlleleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HearsayDAOBeanServiceImpl implements HearsayDAOBeanService {

    // @Autowired
    private AlignmentDAO alignmentDAO;

    // @Autowired
    private ChromosomeDAO chromosomeDAO;

    // @Autowired
    private CanonicalAlleleDAO canonicalAlleleDAO;

    // @Autowired
    private FeatureDAO featureDAO;

    @Autowired
    private GeneDAO geneDAO;

    // @Autowired
    private GeneSymbolDAO geneSymbolDAO;

    // @Autowired
    private IdentifierDAO identifierDAO;

    // @Autowired
    private IntronOffsetDAO intronOffsetDAO;

    @Autowired
    private LocationDAO locationDAO;

    // @Autowired
    private MolecularConsequenceDAO molecularConsequenceDAO;

    // @Autowired
    private PopulationFrequencyDAO populationFrequencyDAO;

    // @Autowired
    private ReferenceCoordinateDAO referenceCoordinateDAO;

    // @Autowired
    private RegionDAO regionDAO;

    @Autowired
    private GenomeReferenceDAO genomeReferenceDAO;

    @Autowired
    private ReferenceSequenceDAO referenceSequenceDAO;

    // @Autowired
    private SimpleAlleleDAO simpleAlleleDAO;

    public HearsayDAOBeanServiceImpl() {
        super();
    }

    @Override
    public AlignmentDAO getAlignmentDAO() {
        return alignmentDAO;
    }

    @Override
    public void setAlignmentDAO(AlignmentDAO alignmentDAO) {
        this.alignmentDAO = alignmentDAO;
    }

    @Override
    public ChromosomeDAO getChromosomeDAO() {
        return chromosomeDAO;
    }

    @Override
    public void setChromosomeDAO(ChromosomeDAO chromosomeDAO) {
        this.chromosomeDAO = chromosomeDAO;
    }

    @Override
    public CanonicalAlleleDAO getCanonicalAlleleDAO() {
        return canonicalAlleleDAO;
    }

    @Override
    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO) {
        this.canonicalAlleleDAO = canonicalAlleleDAO;
    }

    @Override
    public FeatureDAO getFeatureDAO() {
        return featureDAO;
    }

    @Override
    public void setFeatureDAO(FeatureDAO featureDAO) {
        this.featureDAO = featureDAO;
    }

    @Override
    public GeneDAO getGeneDAO() {
        return geneDAO;
    }

    @Override
    public void setGeneDAO(GeneDAO geneDAO) {
        this.geneDAO = geneDAO;
    }

    @Override
    public GeneSymbolDAO getGeneSymbolDAO() {
        return geneSymbolDAO;
    }

    @Override
    public void setGeneSymbolDAO(GeneSymbolDAO geneSymbolDAO) {
        this.geneSymbolDAO = geneSymbolDAO;
    }

    @Override
    public IdentifierDAO getIdentifierDAO() {
        return identifierDAO;
    }

    @Override
    public void setIdentifierDAO(IdentifierDAO identifierDAO) {
        this.identifierDAO = identifierDAO;
    }

    @Override
    public IntronOffsetDAO getIntronOffsetDAO() {
        return intronOffsetDAO;
    }

    @Override
    public void setIntronOffsetDAO(IntronOffsetDAO intronOffsetDAO) {
        this.intronOffsetDAO = intronOffsetDAO;
    }

    @Override
    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    @Override
    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @Override
    public MolecularConsequenceDAO getMolecularConsequenceDAO() {
        return molecularConsequenceDAO;
    }

    @Override
    public void setMolecularConsequenceDAO(MolecularConsequenceDAO molecularConsequenceDAO) {
        this.molecularConsequenceDAO = molecularConsequenceDAO;
    }

    @Override
    public PopulationFrequencyDAO getPopulationFrequencyDAO() {
        return populationFrequencyDAO;
    }

    @Override
    public void setPopulationFrequencyDAO(PopulationFrequencyDAO populationFrequencyDAO) {
        this.populationFrequencyDAO = populationFrequencyDAO;
    }

    @Override
    public ReferenceCoordinateDAO getReferenceCoordinateDAO() {
        return referenceCoordinateDAO;
    }

    @Override
    public void setReferenceCoordinateDAO(ReferenceCoordinateDAO referenceCoordinateDAO) {
        this.referenceCoordinateDAO = referenceCoordinateDAO;
    }

    @Override
    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    @Override
    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    @Override
    public GenomeReferenceDAO getGenomeReferenceDAO() {
        return genomeReferenceDAO;
    }

    @Override
    public void setGenomeReferenceDAO(GenomeReferenceDAO genomeReferenceDAO) {
        this.genomeReferenceDAO = genomeReferenceDAO;
    }

    @Override
    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    @Override
    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

    @Override
    public SimpleAlleleDAO getSimpleAlleleDAO() {
        return simpleAlleleDAO;
    }

    @Override
    public void setSimpleAlleleDAO(SimpleAlleleDAO simpleAlleleDAO) {
        this.simpleAlleleDAO = simpleAlleleDAO;
    }

}
