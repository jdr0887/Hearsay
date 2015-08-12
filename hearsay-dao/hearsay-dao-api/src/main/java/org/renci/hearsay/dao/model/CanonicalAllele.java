package org.renci.hearsay.dao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "canonicalAllele")
@XmlType(propOrder = { "name", "version", "moleculeType", "complexityType", "replacementType", "parent", "children",
        "relatedSimpleAlleles", "relatedIdentifiers" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "canonical_allele")
public class CanonicalAllele extends IdentifiableEntity {

    private static final long serialVersionUID = 179947981554563568L;

    @Column(name = "name")
    private String name;

    @XmlAttribute
    @Column(name = "active")
    private Boolean active;

    @XmlAttribute
    @Column(name = "version")
    private String version;

    @Column(name = "molecule_type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceType moleculeType;

    @Column(name = "complexity_type")
    @Enumerated(EnumType.STRING)
    private ComplexityType complexityType;

    @Column(name = "replacement_type")
    @Enumerated(EnumType.STRING)
    private ReplacementType replacementType;

    @XmlAttribute
    @Column(name = "split")
    private Boolean split;

    @XmlElementWrapper(name = "simpleAlleles")
    @XmlElement(name = "simpleAllele")
    @OneToMany(mappedBy = "canonicalAllele", fetch = FetchType.LAZY)
    private List<SimpleAllele> simpleAlleles;

    public CanonicalAllele() {
        super();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReferenceSequenceType getMoleculeType() {
        return moleculeType;
    }

    public void setMoleculeType(ReferenceSequenceType moleculeType) {
        this.moleculeType = moleculeType;
    }

    public ComplexityType getComplexityType() {
        return complexityType;
    }

    public void setComplexityType(ComplexityType complexityType) {
        this.complexityType = complexityType;
    }

    public ReplacementType getReplacementType() {
        return replacementType;
    }

    public void setReplacementType(ReplacementType replacementType) {
        this.replacementType = replacementType;
    }

    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
    }

    @Override
    public String toString() {
        return String
                .format("CanonicalAllele [id=%s, name=%s, active=%s, version=%s, moleculeType=%s, complexityType=%s, replacementType=%s, split=%s]",
                        id, name, active, version, moleculeType, complexityType, replacementType, split);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((complexityType == null) ? 0 : complexityType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((replacementType == null) ? 0 : replacementType.hashCode());
        result = prime * result + ((split == null) ? 0 : split.hashCode());
        result = prime * result + ((moleculeType == null) ? 0 : moleculeType.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        CanonicalAllele other = (CanonicalAllele) obj;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        if (complexityType != other.complexityType)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (replacementType != other.replacementType)
            return false;
        if (split == null) {
            if (other.split != null)
                return false;
        } else if (!split.equals(other.split))
            return false;
        if (moleculeType != other.moleculeType)
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
