package org.renci.hearsay.dao;

public interface HearsayDAOBeanService {

    public AlignmentDAO getAlignmentDAO();

    public void setAlignmentDAO(AlignmentDAO alignmentDAO);

    public ChromosomeDAO getChromosomeDAO();

    public void setChromosomeDAO(ChromosomeDAO chromosomeDAO);

    public CanonicalAlleleDAO getCanonicalAlleleDAO();

    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO);

    public FeatureDAO getFeatureDAO();

    public void setFeatureDAO(FeatureDAO featureDAO);

    public GeneDAO getGeneDAO();

    public void setGeneDAO(GeneDAO geneDAO);

    public GeneSymbolDAO getGeneSymbolDAO();

    public void setGeneSymbolDAO(GeneSymbolDAO geneSymbolDAO);

    public IdentifierDAO getIdentifierDAO();

    public void setIdentifierDAO(IdentifierDAO identifierDAO);

    public IntronOffsetDAO getIntronOffsetDAO();

    public void setIntronOffsetDAO(IntronOffsetDAO intronOffsetDAO);

    public LocationDAO getLocationDAO();

    public void setLocationDAO(LocationDAO locationDAO);

    public MolecularConsequenceDAO getMolecularConsequenceDAO();

    public void setMolecularConsequenceDAO(MolecularConsequenceDAO molecularConsequenceDAO);

    public PopulationFrequencyDAO getPopulationFrequencyDAO();

    public void setPopulationFrequencyDAO(PopulationFrequencyDAO populationFrequencyDAO);

    public ReferenceCoordinateDAO getReferenceCoordinateDAO();

    public void setReferenceCoordinateDAO(ReferenceCoordinateDAO referenceCoordinateDAO);

    public RegionDAO getRegionDAO();

    public void setRegionDAO(RegionDAO regionDAO);

    public GenomeReferenceDAO getGenomeReferenceDAO();

    public void setGenomeReferenceDAO(GenomeReferenceDAO genomeReferenceDAO);

    public ReferenceSequenceDAO getReferenceSequenceDAO();

    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO);

    public SimpleAlleleDAO getSimpleAlleleDAO();

    public void setSimpleAlleleDAO(SimpleAlleleDAO simpleAlleleDAO);

}