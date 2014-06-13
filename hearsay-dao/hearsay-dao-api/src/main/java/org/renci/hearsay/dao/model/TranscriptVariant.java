package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcriptVariant")
@XmlType(name = "TranscriptVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_variant")
public class TranscriptVariant extends BaseEntity {

    private static final long serialVersionUID = 6501339578644148679L;

    @Column(name = "transcript_position")
    private Integer transcriptPosition;

    @Column(name = "coding_position")
    private Integer codingPosition;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "transcript_variant_variant_effect", joinColumns = @JoinColumn(name = "transcript_variant_fid"), inverseJoinColumns = @JoinColumn(name = "variant_effect_fid"))
    private Set<VariantEffect> variantEffects;

    public TranscriptVariant() {
        super();
    }

    public Integer getTranscriptPosition() {
        return transcriptPosition;
    }

    public void setTranscriptPosition(Integer transcriptPosition) {
        this.transcriptPosition = transcriptPosition;
    }

    public Integer getCodingPosition() {
        return codingPosition;
    }

    public void setCodingPosition(Integer codingPosition) {
        this.codingPosition = codingPosition;
    }

    public Set<VariantEffect> getVariantEffects() {
        return variantEffects;
    }

    public void setVariantEffects(Set<VariantEffect> variantEffects) {
        this.variantEffects = variantEffects;
    }

    @Override
    public String toString() {
        return String.format("TranscriptVariant [transcriptPosition=%s, codingPosition=%s, id=%s]", transcriptPosition,
                codingPosition, id);
    }

}
