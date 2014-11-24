package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "variantRepresentation")
@XmlType(name = "VariantRepresentation")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_representation")
public class VariantRepresentation extends BaseEntity {

    private static final long serialVersionUID = 6277477885518977679L;

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

    @OneToMany(mappedBy = "variant", fetch = FetchType.EAGER)
    private Set<MolecularConsequence> consequences;

    public VariantRepresentation() {
        super();
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
        return String.format("VariantRepresentation [hgvs=%s, start=%s, stop=%s]", hgvs, start, stop);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((hgvs == null) ? 0 : hgvs.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((stop == null) ? 0 : stop.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        VariantRepresentation other = (VariantRepresentation) obj;
        if (hgvs == null) {
            if (other.hgvs != null)
                return false;
        } else if (!hgvs.equals(other.hgvs))
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
