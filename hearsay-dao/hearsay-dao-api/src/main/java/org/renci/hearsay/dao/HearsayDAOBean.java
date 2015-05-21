package org.renci.hearsay.dao;

public class HearsayDAOBean {

    private CanonicalAlleleDAO canonicalAlleleDAO;

    private GeneDAO geneDAO;

    private GeneSymbolDAO geneSymbolDAO;

    private IdentifierDAO identifierDAO;

    private IntronOffsetDAO intronOffsetDAO;

    private ReferenceCoordinateDAO referenceCoordinateDAO;

    private ReferenceGenomeDAO referenceGenomeDAO;

    private ReferenceSequenceDAO referenceSequenceDAO;

    private SimpleAlleleDAO simpleAlleleDAO;

    public HearsayDAOBean() {
        super();
    }

    public CanonicalAlleleDAO getCanonicalAlleleDAO() {
        return canonicalAlleleDAO;
    }

    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO) {
        this.canonicalAlleleDAO = canonicalAlleleDAO;
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

    public IntronOffsetDAO getIntronOffsetDAO() {
        return intronOffsetDAO;
    }

    public void setIntronOffsetDAO(IntronOffsetDAO intronOffsetDAO) {
        this.intronOffsetDAO = intronOffsetDAO;
    }

    public ReferenceCoordinateDAO getReferenceCoordinateDAO() {
        return referenceCoordinateDAO;
    }

    public void setReferenceCoordinateDAO(ReferenceCoordinateDAO referenceCoordinateDAO) {
        this.referenceCoordinateDAO = referenceCoordinateDAO;
    }

    public ReferenceGenomeDAO getReferenceGenomeDAO() {
        return referenceGenomeDAO;
    }

    public void setReferenceGenomeDAO(ReferenceGenomeDAO referenceGenomeDAO) {
        this.referenceGenomeDAO = referenceGenomeDAO;
    }

    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

    public SimpleAlleleDAO getSimpleAlleleDAO() {
        return simpleAlleleDAO;
    }

    public void setSimpleAlleleDAO(SimpleAlleleDAO simpleAlleleDAO) {
        this.simpleAlleleDAO = simpleAlleleDAO;
    }

}
