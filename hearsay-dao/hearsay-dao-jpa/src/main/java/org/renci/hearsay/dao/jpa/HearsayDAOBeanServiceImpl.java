package org.renci.hearsay.dao.jpa;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
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
    private FeatureDAO featureDAO;

    @Inject
    private GeneDAO geneDAO;

    @Inject
    private GeneSymbolDAO geneSymbolDAO;

    @Inject
    private IdentifierDAO identifierDAO;

    @Inject
    private IntronOffsetDAO intronOffsetDAO;

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

    @Inject
    private SimpleAlleleDAO simpleAlleleDAO;

    public HearsayDAOBeanServiceImpl() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getAlignmentDAO()
     */
    @Override
    public AlignmentDAO getAlignmentDAO() {
        return alignmentDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setAlignmentDAO(org.renci.hearsay.dao.AlignmentDAO)
     */
    @Override
    public void setAlignmentDAO(AlignmentDAO alignmentDAO) {
        this.alignmentDAO = alignmentDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getChromosomeDAO()
     */
    @Override
    public ChromosomeDAO getChromosomeDAO() {
        return chromosomeDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setChromosomeDAO(org.renci.hearsay.dao.ChromosomeDAO)
     */
    @Override
    public void setChromosomeDAO(ChromosomeDAO chromosomeDAO) {
        this.chromosomeDAO = chromosomeDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getCanonicalAlleleDAO()
     */
    @Override
    public CanonicalAlleleDAO getCanonicalAlleleDAO() {
        return canonicalAlleleDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setCanonicalAlleleDAO(org.renci.hearsay.dao.CanonicalAlleleDAO)
     */
    @Override
    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO) {
        this.canonicalAlleleDAO = canonicalAlleleDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getFeatureDAO()
     */
    @Override
    public FeatureDAO getFeatureDAO() {
        return featureDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setFeatureDAO(org.renci.hearsay.dao.FeatureDAO)
     */
    @Override
    public void setFeatureDAO(FeatureDAO featureDAO) {
        this.featureDAO = featureDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getGeneDAO()
     */
    @Override
    public GeneDAO getGeneDAO() {
        return geneDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setGeneDAO(org.renci.hearsay.dao.GeneDAO)
     */
    @Override
    public void setGeneDAO(GeneDAO geneDAO) {
        this.geneDAO = geneDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getGeneSymbolDAO()
     */
    @Override
    public GeneSymbolDAO getGeneSymbolDAO() {
        return geneSymbolDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setGeneSymbolDAO(org.renci.hearsay.dao.GeneSymbolDAO)
     */
    @Override
    public void setGeneSymbolDAO(GeneSymbolDAO geneSymbolDAO) {
        this.geneSymbolDAO = geneSymbolDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getIdentifierDAO()
     */
    @Override
    public IdentifierDAO getIdentifierDAO() {
        return identifierDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setIdentifierDAO(org.renci.hearsay.dao.IdentifierDAO)
     */
    @Override
    public void setIdentifierDAO(IdentifierDAO identifierDAO) {
        this.identifierDAO = identifierDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getIntronOffsetDAO()
     */
    @Override
    public IntronOffsetDAO getIntronOffsetDAO() {
        return intronOffsetDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setIntronOffsetDAO(org.renci.hearsay.dao.IntronOffsetDAO)
     */
    @Override
    public void setIntronOffsetDAO(IntronOffsetDAO intronOffsetDAO) {
        this.intronOffsetDAO = intronOffsetDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getLocationDAO()
     */
    @Override
    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setLocationDAO(org.renci.hearsay.dao.LocationDAO)
     */
    @Override
    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getMolecularConsequenceDAO()
     */
    @Override
    public MolecularConsequenceDAO getMolecularConsequenceDAO() {
        return molecularConsequenceDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setMolecularConsequenceDAO(org.renci.hearsay.dao.
     * MolecularConsequenceDAO)
     */
    @Override
    public void setMolecularConsequenceDAO(MolecularConsequenceDAO molecularConsequenceDAO) {
        this.molecularConsequenceDAO = molecularConsequenceDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getPopulationFrequencyDAO()
     */
    @Override
    public PopulationFrequencyDAO getPopulationFrequencyDAO() {
        return populationFrequencyDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setPopulationFrequencyDAO(org.renci.hearsay.dao.
     * PopulationFrequencyDAO)
     */
    @Override
    public void setPopulationFrequencyDAO(PopulationFrequencyDAO populationFrequencyDAO) {
        this.populationFrequencyDAO = populationFrequencyDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getReferenceCoordinateDAO()
     */
    @Override
    public ReferenceCoordinateDAO getReferenceCoordinateDAO() {
        return referenceCoordinateDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setReferenceCoordinateDAO(org.renci.hearsay.dao.
     * ReferenceCoordinateDAO)
     */
    @Override
    public void setReferenceCoordinateDAO(ReferenceCoordinateDAO referenceCoordinateDAO) {
        this.referenceCoordinateDAO = referenceCoordinateDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getRegionDAO()
     */
    @Override
    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setRegionDAO(org.renci.hearsay.dao.RegionDAO)
     */
    @Override
    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getGenomeReferenceDAO()
     */
    @Override
    public GenomeReferenceDAO getGenomeReferenceDAO() {
        return genomeReferenceDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setGenomeReferenceDAO(org.renci.hearsay.dao.GenomeReferenceDAO)
     */
    @Override
    public void setGenomeReferenceDAO(GenomeReferenceDAO genomeReferenceDAO) {
        this.genomeReferenceDAO = genomeReferenceDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getReferenceSequenceDAO()
     */
    @Override
    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.renci.hearsay.dao.HearsayDAOBeanService#setReferenceSequenceDAO(org.renci.hearsay.dao.ReferenceSequenceDAO)
     */
    @Override
    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#getSimpleAlleleDAO()
     */
    @Override
    public SimpleAlleleDAO getSimpleAlleleDAO() {
        return simpleAlleleDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.renci.hearsay.dao.HearsayDAOBeanService#setSimpleAlleleDAO(org.renci.hearsay.dao.SimpleAlleleDAO)
     */
    @Override
    public void setSimpleAlleleDAO(SimpleAlleleDAO simpleAlleleDAO) {
        this.simpleAlleleDAO = simpleAlleleDAO;
    }

}
