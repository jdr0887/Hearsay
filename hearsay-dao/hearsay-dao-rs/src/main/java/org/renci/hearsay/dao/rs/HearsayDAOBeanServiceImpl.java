package org.renci.hearsay.dao.rs;

import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.CanonicalAlleleReplacementDAO;
import org.renci.hearsay.dao.ChromosomeDAO;
import org.renci.hearsay.dao.ContextualAlleleDAO;
import org.renci.hearsay.dao.ContextualAlleleNameDAO;
import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.IdentifierDAO;
import org.renci.hearsay.dao.ExternalOffsetPositionDAO;
import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.PopulationFrequencyDAO;
import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.RegionDAO;
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
    private CanonicalAlleleReplacementDAO canonicalAlleleReplacementDAO;

    // @Autowired
    private ContextualAlleleDAO contextualAlleleDAO;

    // @Autowired
    private ContextualAlleleNameDAO contextualAlleleNameDAO;

    // @Autowired
    private FeatureDAO featureDAO;

    @Autowired
    private GeneDAO geneDAO;

    // @Autowired
    private GeneSymbolDAO geneSymbolDAO;

    // @Autowired
    private IdentifierDAO identifierDAO;

    // @Autowired
    private ExternalOffsetPositionDAO externalOffsetPositionDAO;

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

    public HearsayDAOBeanServiceImpl() {
        super();
    }

    public AlignmentDAO getAlignmentDAO() {
        return alignmentDAO;
    }

    public void setAlignmentDAO(AlignmentDAO alignmentDAO) {
        this.alignmentDAO = alignmentDAO;
    }

    public ChromosomeDAO getChromosomeDAO() {
        return chromosomeDAO;
    }

    public void setChromosomeDAO(ChromosomeDAO chromosomeDAO) {
        this.chromosomeDAO = chromosomeDAO;
    }

    public CanonicalAlleleDAO getCanonicalAlleleDAO() {
        return canonicalAlleleDAO;
    }

    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO) {
        this.canonicalAlleleDAO = canonicalAlleleDAO;
    }

    public CanonicalAlleleReplacementDAO getCanonicalAlleleReplacementDAO() {
        return canonicalAlleleReplacementDAO;
    }

    public void setCanonicalAlleleReplacementDAO(CanonicalAlleleReplacementDAO canonicalAlleleReplacementDAO) {
        this.canonicalAlleleReplacementDAO = canonicalAlleleReplacementDAO;
    }

    public ContextualAlleleDAO getContextualAlleleDAO() {
        return contextualAlleleDAO;
    }

    public void setContextualAlleleDAO(ContextualAlleleDAO contextualAlleleDAO) {
        this.contextualAlleleDAO = contextualAlleleDAO;
    }

    public ContextualAlleleNameDAO getContextualAlleleNameDAO() {
        return contextualAlleleNameDAO;
    }

    public void setContextualAlleleNameDAO(ContextualAlleleNameDAO contextualAlleleNameDAO) {
        this.contextualAlleleNameDAO = contextualAlleleNameDAO;
    }

    public FeatureDAO getFeatureDAO() {
        return featureDAO;
    }

    public void setFeatureDAO(FeatureDAO featureDAO) {
        this.featureDAO = featureDAO;
    }

    public GeneDAO getGeneDAO() {
        return geneDAO;
    }

    public void setGeneDAO(GeneDAO geneDAO) {
        this.geneDAO = geneDAO;
    }

    public GeneSymbolDAO getGeneSymbolDAO() {
        return geneSymbolDAO;
    }

    public void setGeneSymbolDAO(GeneSymbolDAO geneSymbolDAO) {
        this.geneSymbolDAO = geneSymbolDAO;
    }

    public IdentifierDAO getIdentifierDAO() {
        return identifierDAO;
    }

    public void setIdentifierDAO(IdentifierDAO identifierDAO) {
        this.identifierDAO = identifierDAO;
    }

    public ExternalOffsetPositionDAO getExternalOffsetPositionDAO() {
        return externalOffsetPositionDAO;
    }

    public void setExternalOffsetPositionDAO(ExternalOffsetPositionDAO externalOffsetPositionDAO) {
        this.externalOffsetPositionDAO = externalOffsetPositionDAO;
    }

    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public MolecularConsequenceDAO getMolecularConsequenceDAO() {
        return molecularConsequenceDAO;
    }

    public void setMolecularConsequenceDAO(MolecularConsequenceDAO molecularConsequenceDAO) {
        this.molecularConsequenceDAO = molecularConsequenceDAO;
    }

    public PopulationFrequencyDAO getPopulationFrequencyDAO() {
        return populationFrequencyDAO;
    }

    public void setPopulationFrequencyDAO(PopulationFrequencyDAO populationFrequencyDAO) {
        this.populationFrequencyDAO = populationFrequencyDAO;
    }

    public ReferenceCoordinateDAO getReferenceCoordinateDAO() {
        return referenceCoordinateDAO;
    }

    public void setReferenceCoordinateDAO(ReferenceCoordinateDAO referenceCoordinateDAO) {
        this.referenceCoordinateDAO = referenceCoordinateDAO;
    }

    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    public GenomeReferenceDAO getGenomeReferenceDAO() {
        return genomeReferenceDAO;
    }

    public void setGenomeReferenceDAO(GenomeReferenceDAO genomeReferenceDAO) {
        this.genomeReferenceDAO = genomeReferenceDAO;
    }

    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

}
