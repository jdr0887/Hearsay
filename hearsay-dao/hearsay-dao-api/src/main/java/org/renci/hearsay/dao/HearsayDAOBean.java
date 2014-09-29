package org.renci.hearsay.dao;

public class HearsayDAOBean {

    private GeneDAO geneDAO;

    private GenomicVariantDAO genomicSequenceVariantDAO;

    private TranscriptAlignmentDAO mappedTranscriptDAO;

    private GenomicRefSeqDAO referenceGenomeDAO;

    private ReferenceSequenceDAO referenceSequenceDAO;

    private RegionDAO regionDAO;

    private TranscriptRefSeqDAO transcriptDAO;

    private TranscriptVariantDAO transcriptVariantDAO;

    private TranslationRefSeqDAO translationDAO;

    private TranslationVariantDAO translationVariantDAO;

    private CanonicalVariantDAO variantDAO;

    public HearsayDAOBean() {
        super();
    }

    public GeneDAO getGeneDAO() {
        return geneDAO;
    }

    public void setGeneDAO(GeneDAO geneDAO) {
        this.geneDAO = geneDAO;
    }

    public GenomicVariantDAO getGenomicSequenceVariantDAO() {
        return genomicSequenceVariantDAO;
    }

    public void setGenomicSequenceVariantDAO(GenomicVariantDAO genomicSequenceVariantDAO) {
        this.genomicSequenceVariantDAO = genomicSequenceVariantDAO;
    }

    public TranscriptAlignmentDAO getMappedTranscriptDAO() {
        return mappedTranscriptDAO;
    }

    public void setMappedTranscriptDAO(TranscriptAlignmentDAO mappedTranscriptDAO) {
        this.mappedTranscriptDAO = mappedTranscriptDAO;
    }

    public GenomicRefSeqDAO getReferenceGenomeDAO() {
        return referenceGenomeDAO;
    }

    public void setReferenceGenomeDAO(GenomicRefSeqDAO referenceGenomeDAO) {
        this.referenceGenomeDAO = referenceGenomeDAO;
    }

    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    public TranscriptRefSeqDAO getTranscriptDAO() {
        return transcriptDAO;
    }

    public void setTranscriptDAO(TranscriptRefSeqDAO transcriptDAO) {
        this.transcriptDAO = transcriptDAO;
    }

    public TranscriptVariantDAO getTranscriptVariantDAO() {
        return transcriptVariantDAO;
    }

    public void setTranscriptVariantDAO(TranscriptVariantDAO transcriptVariantDAO) {
        this.transcriptVariantDAO = transcriptVariantDAO;
    }

    public TranslationRefSeqDAO getTranslationDAO() {
        return translationDAO;
    }

    public void setTranslationDAO(TranslationRefSeqDAO translationDAO) {
        this.translationDAO = translationDAO;
    }

    public TranslationVariantDAO getTranslationVariantDAO() {
        return translationVariantDAO;
    }

    public void setTranslationVariantDAO(TranslationVariantDAO translationVariantDAO) {
        this.translationVariantDAO = translationVariantDAO;
    }

    public CanonicalVariantDAO getVariantDAO() {
        return variantDAO;
    }

    public void setVariantDAO(CanonicalVariantDAO variantDAO) {
        this.variantDAO = variantDAO;
    }

}
