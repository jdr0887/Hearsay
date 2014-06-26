package org.renci.hearsay.dao;

public class HearsayDAOBean {

    private GeneDAO geneDAO;

    private GenomicSequenceVariantDAO genomicSequenceVariantDAO;

    private MappedTranscriptDAO mappedTranscriptDAO;

    private ReferenceGenomeDAO referenceGenomeDAO;

    private ReferenceSequenceDAO referenceSequenceDAO;

    private RegionDAO regionDAO;

    private TranscriptDAO transcriptDAO;

    private TranscriptVariantDAO transcriptVariantDAO;

    private TranslationDAO translationDAO;

    private TranslationVariantDAO translationVariantDAO;

    private VariantDAO variantDAO;

    private VariantEffectDAO variantEffectDAO;

    public HearsayDAOBean() {
        super();
    }

    public GeneDAO getGeneDAO() {
        return geneDAO;
    }

    public void setGeneDAO(GeneDAO geneDAO) {
        this.geneDAO = geneDAO;
    }

    public GenomicSequenceVariantDAO getGenomicSequenceVariantDAO() {
        return genomicSequenceVariantDAO;
    }

    public void setGenomicSequenceVariantDAO(GenomicSequenceVariantDAO genomicSequenceVariantDAO) {
        this.genomicSequenceVariantDAO = genomicSequenceVariantDAO;
    }

    public MappedTranscriptDAO getMappedTranscriptDAO() {
        return mappedTranscriptDAO;
    }

    public void setMappedTranscriptDAO(MappedTranscriptDAO mappedTranscriptDAO) {
        this.mappedTranscriptDAO = mappedTranscriptDAO;
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

    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    public TranscriptDAO getTranscriptDAO() {
        return transcriptDAO;
    }

    public void setTranscriptDAO(TranscriptDAO transcriptDAO) {
        this.transcriptDAO = transcriptDAO;
    }

    public TranscriptVariantDAO getTranscriptVariantDAO() {
        return transcriptVariantDAO;
    }

    public void setTranscriptVariantDAO(TranscriptVariantDAO transcriptVariantDAO) {
        this.transcriptVariantDAO = transcriptVariantDAO;
    }

    public TranslationDAO getTranslationDAO() {
        return translationDAO;
    }

    public void setTranslationDAO(TranslationDAO translationDAO) {
        this.translationDAO = translationDAO;
    }

    public TranslationVariantDAO getTranslationVariantDAO() {
        return translationVariantDAO;
    }

    public void setTranslationVariantDAO(TranslationVariantDAO translationVariantDAO) {
        this.translationVariantDAO = translationVariantDAO;
    }

    public VariantDAO getVariantDAO() {
        return variantDAO;
    }

    public void setVariantDAO(VariantDAO variantDAO) {
        this.variantDAO = variantDAO;
    }

    public VariantEffectDAO getVariantEffectDAO() {
        return variantEffectDAO;
    }

    public void setVariantEffectDAO(VariantEffectDAO variantEffectDAO) {
        this.variantEffectDAO = variantEffectDAO;
    }

}
