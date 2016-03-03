package org.renci.hearsay.dao.model;

import java.util.ArrayList;
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

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "canonicalAllele")
@XmlType(propOrder = { "active", "version", "type", "complexityType", "replacements", "contextualAlleles" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "canonical_allele")
@FetchGroups({ @FetchGroup(name = "includeAll", attributes = { @FetchAttribute(name = "replacements"),
        @FetchAttribute(name = "contextualAlleles") }) })
public class CanonicalAllele extends IdentifiableEntity {

    private static final long serialVersionUID = 179947981554563568L;

    @XmlAttribute
    @Column(name = "active")
    private Boolean active;

    @XmlAttribute
    @Column(name = "version")
    private String version;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CanonicalAlleleType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "complexity_type")
    private ComplexityType complexityType;

    @XmlElementWrapper(name = "replacements")
    @XmlElement(name = "replacements")
    @OneToMany(mappedBy = "canonicalAllele", fetch = FetchType.LAZY)
    private List<CanonicalAlleleReplacement> replacements;

    @XmlElementWrapper(name = "contextualAlleles")
    @XmlElement(name = "contextualAllele")
    @OneToMany(mappedBy = "canonicalAllele", fetch = FetchType.LAZY)
    private List<ContextualAllele> contextualAlleles;

    public CanonicalAllele() {
        super();
        this.replacements = new ArrayList<CanonicalAlleleReplacement>();
        this.contextualAlleles = new ArrayList<ContextualAllele>();
    }

    public CanonicalAllele(CanonicalAlleleType type) {
        this();
        this.type = type;
    }

    public List<ContextualAllele> getContextualAlleles() {
        return contextualAlleles;
    }

    public void setContextualAlleles(List<ContextualAllele> contextualAlleles) {
        this.contextualAlleles = contextualAlleles;
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

    public CanonicalAlleleType getType() {
        return type;
    }

    public void setType(CanonicalAlleleType type) {
        this.type = type;
    }

    public ComplexityType getComplexityType() {
        return complexityType;
    }

    public void setComplexityType(ComplexityType complexityType) {
        this.complexityType = complexityType;
    }

    public List<CanonicalAlleleReplacement> getReplacements() {
        return replacements;
    }

    public void setReplacements(List<CanonicalAlleleReplacement> replacements) {
        this.replacements = replacements;
    }

    @Override
    public String toString() {
        return String.format("CanonicalAllele [id=%s, active=%s, version=%s, type=%s, complexityType=%s]", id, active, version, type,
                complexityType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((complexityType == null) ? 0 : complexityType.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        CanonicalAllele other = (CanonicalAllele) obj;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        if (complexityType != other.complexityType)
            return false;
        if (type != other.type)
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
