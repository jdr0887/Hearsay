package org.renci.hearsay.dao.jpa;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.CanonicalAlleleReplacementDAO;
import org.renci.hearsay.dao.ChromosomeDAO;
import org.renci.hearsay.dao.ContextualAlleleDAO;
import org.renci.hearsay.dao.ContextualAlleleNameDAO;
import org.renci.hearsay.dao.ExternalOffsetPositionDAO;
import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.IdentifierDAO;
import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.PopulationFrequencyDAO;
import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.RegionDAO;

@OsgiServiceProvider(classes = { HearsayDAOBeanService.class })
@Singleton
public class HearsayDAOBeanServiceImpl implements HearsayDAOBeanService {

    @Inject
    private AlignmentDAO alignmentDAO;

    @Inject
    private ChromosomeDAO chromosomeDAO;

    @Inject
    private CanonicalAlleleDAO canonicalAlleleDAO;

    @Inject
    private CanonicalAlleleReplacementDAO canonicalAlleleReplacementDAO;

    @Inject
    private ContextualAlleleDAO contextualAlleleDAO;

    @Inject
    private ContextualAlleleNameDAO contextualAlleleNameDAO;

    @Inject
    private FeatureDAO featureDAO;

    @Inject
    private GeneDAO geneDAO;

    @Inject
    private GeneSymbolDAO geneSymbolDAO;

    @Inject
    private IdentifierDAO identifierDAO;

    @Inject
    private ExternalOffsetPositionDAO externalOffsetPositionDAO;

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private MolecularConsequenceDAO molecularConsequenceDAO;

    @Inject
    private PopulationFrequencyDAO populationFrequencyDAO;

    @Inject
    private ReferenceCoordinateDAO referenceCoordinateDAO;

    @Inject
    private RegionDAO regionDAO;

    @Inject
    private GenomeReferenceDAO genomeReferenceDAO;

    @Inject
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
