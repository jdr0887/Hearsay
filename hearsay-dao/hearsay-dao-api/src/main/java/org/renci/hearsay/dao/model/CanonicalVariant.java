package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "canonicalVariant")
@XmlType(name = "CanonicalVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "canonical_variant")
public class CanonicalVariant implements Persistable {

    private static final long serialVersionUID = -9258968674837262L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canonical_variant_id_seq")
    @SequenceGenerator(name = "canonical_variant_id_seq", sequenceName = "canonical_variant_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "canonicalVariant", fetch = FetchType.EAGER)
    private Set<GenomicVariant> genomicVariants;

    @JsonIgnore
    @OneToMany(mappedBy = "canonicalVariant", fetch = FetchType.EAGER)
    private Set<TranscriptVariant> transcriptVariants;

    @JsonIgnore
    @OneToMany(mappedBy = "canonicalVariant", fetch = FetchType.EAGER)
    private Set<TranslationVariant> translationVariants;

    @Transient
    private Set<VariantRepresentation> variants = new HashSet<VariantRepresentation>();

    public CanonicalVariant() {
        super();
    }

    public Set<VariantRepresentation> getVariants() {
        variants.addAll(genomicVariants);
        variants.addAll(transcriptVariants);
        variants.addAll(translationVariants);
        return variants;
    }

    public void setVariants(Set<VariantRepresentation> variants) {
        this.variants = variants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GenomicVariant> getGenomicVariants() {
        return genomicVariants;
    }

    public void setGenomicVariants(Set<GenomicVariant> genomicVariants) {
        this.genomicVariants = genomicVariants;
    }

    public Set<TranscriptVariant> getTranscriptVariants() {
        return transcriptVariants;
    }

    public void setTranscriptVariants(Set<TranscriptVariant> transcriptVariants) {
        this.transcriptVariants = transcriptVariants;
    }

    public Set<TranslationVariant> getTranslationVariants() {
        return translationVariants;
    }

    public void setTranslationVariants(Set<TranslationVariant> translationVariants) {
        this.translationVariants = translationVariants;
    }

    @Override
    public String toString() {
        return String.format("CanonicalVariant [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CanonicalVariant other = (CanonicalVariant) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
