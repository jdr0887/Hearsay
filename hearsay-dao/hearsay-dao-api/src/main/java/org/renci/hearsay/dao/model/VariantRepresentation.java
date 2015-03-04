package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@XmlRootElement(name = "variantRepresentation")
@XmlType(name = "VariantRepresentation")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_representation")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class VariantRepresentation implements Persistable {

    private static final long serialVersionUID = 6277477885518977679L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_representation_id_seq")
    @SequenceGenerator(name = "variant_representation_id_seq", sequenceName = "variant_representation_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    protected Long id;

    @Lob
    @Column(name = "hgvs")
    private String hgvs;

    @Column(name = "start")
    private Integer start;

    @Column(name = "stop")
    private Integer stop;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "canonical_variant_fid")
    private CanonicalVariant canonicalVariant;

    @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
    private Set<MolecularConsequence> consequences;

    @OneToMany(mappedBy = "variantRepresentation", fetch = FetchType.LAZY)
    private Set<PopulationFrequency> populationFrequencies;

    public VariantRepresentation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getHgvs() {
        return hgvs;
    }

    public void setHgvs(String hgvs) {
        this.hgvs = hgvs;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public Set<PopulationFrequency> getPopulationFrequencies() {
        return populationFrequencies;
    }

    public void setPopulationFrequencies(Set<PopulationFrequency> populationFrequencies) {
        this.populationFrequencies = populationFrequencies;
    }

    public CanonicalVariant getCanonicalVariant() {
        return canonicalVariant;
    }

    public void setCanonicalVariant(CanonicalVariant canonicalVariant) {
        this.canonicalVariant = canonicalVariant;
    }

    public Set<MolecularConsequence> getConsequences() {
        return consequences;
    }

    public void setConsequences(Set<MolecularConsequence> consequences) {
        this.consequences = consequences;
    }

    @Override
    public String toString() {
        return String.format("VariantRepresentation [id=%s, hgvs=%s, start=%s, stop=%s]", id, hgvs, start, stop);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hgvs == null) ? 0 : hgvs.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((stop == null) ? 0 : stop.hashCode());
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
        VariantRepresentation other = (VariantRepresentation) obj;
        if (hgvs == null) {
            if (other.hgvs != null)
                return false;
        } else if (!hgvs.equals(other.hgvs))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (stop == null) {
            if (other.stop != null)
                return false;
        } else if (!stop.equals(other.stop))
            return false;
        return true;
    }

}
