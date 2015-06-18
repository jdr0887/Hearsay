package org.renci.hearsay.dao.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "simpleAllele")
@XmlType(name = "SimpleAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_allele")
public class SimpleAllele extends IdentifiableEntity {

    private static final long serialVersionUID = 608874481580966242L;

    @ManyToOne
    @JoinColumn(name = "canonical_allele_fid")
    private CanonicalAllele canonicalAllele;

    @XmlAttribute
    @Column(name = "allele")
    private String allele;

    @XmlAttribute
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @XmlAttribute
    private SimpleAlleleType type;

    @Column(name = "primary_change_so_id")
    private Integer primaryChangeSOId;

    @Column(name = "ancillary_change_so_id")
    private Integer ancillaryChangeSOId;

    @ManyToOne
    @JoinColumn(name = "reference_coordinate_fid")
    private ReferenceCoordinate referenceCoordinate;

    @ManyToOne
    @JoinColumn(name = "parent_fid")
    private SimpleAllele parent;

    @OneToMany(mappedBy = "parent")
    private Collection<SimpleAllele> related;

    public SimpleAllele() {
        super();
    }

    public CanonicalAllele getCanonicalAllele() {
        return canonicalAllele;
    }

    public void setCanonicalAllele(CanonicalAllele canonicalAllele) {
        this.canonicalAllele = canonicalAllele;
    }

    public String getAllele() {
        return allele;
    }

    public void setAllele(String allele) {
        this.allele = allele;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleAlleleType getType() {
        return type;
    }

    public void setType(SimpleAlleleType type) {
        this.type = type;
    }

    public Integer getPrimaryChangeSOId() {
        return primaryChangeSOId;
    }

    public void setPrimaryChangeSOId(Integer primaryChangeSOId) {
        this.primaryChangeSOId = primaryChangeSOId;
    }

    public Integer getAncillaryChangeSOId() {
        return ancillaryChangeSOId;
    }

    public void setAncillaryChangeSOId(Integer ancillaryChangeSOId) {
        this.ancillaryChangeSOId = ancillaryChangeSOId;
    }

    public ReferenceCoordinate getReferenceCoordinate() {
        return referenceCoordinate;
    }

    public void setReferenceCoordinate(ReferenceCoordinate referenceCoordinate) {
        this.referenceCoordinate = referenceCoordinate;
    }

    public SimpleAllele getParent() {
        return parent;
    }

    public void setParent(SimpleAllele parent) {
        this.parent = parent;
    }

    public Collection<SimpleAllele> getRelated() {
        return related;
    }

    public void setRelated(Collection<SimpleAllele> related) {
        this.related = related;
    }

    @Override
    public String toString() {
        return String.format(
                "SimpleAllele [id=%s, allele=%s, name=%s, type=%s, primaryChangeSOId=%s, ancillaryChangeSOId=%s]", id,
                allele, name, type, primaryChangeSOId, ancillaryChangeSOId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((allele == null) ? 0 : allele.hashCode());
        result = prime * result + ((ancillaryChangeSOId == null) ? 0 : ancillaryChangeSOId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((primaryChangeSOId == null) ? 0 : primaryChangeSOId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        SimpleAllele other = (SimpleAllele) obj;
        if (allele == null) {
            if (other.allele != null)
                return false;
        } else if (!allele.equals(other.allele))
            return false;
        if (ancillaryChangeSOId == null) {
            if (other.ancillaryChangeSOId != null)
                return false;
        } else if (!ancillaryChangeSOId.equals(other.ancillaryChangeSOId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (primaryChangeSOId == null) {
            if (other.primaryChangeSOId != null)
                return false;
        } else if (!primaryChangeSOId.equals(other.primaryChangeSOId))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
