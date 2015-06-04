package org.renci.hearsay.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "canonicalAllele")
@XmlType(name = "CanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "canonical_allele")
public class CanonicalAllele implements Persistable {

    private static final long serialVersionUID = 179947981554563568L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canonical_allele_id_seq")
    @SequenceGenerator(name = "canonical_allele_id_seq", sequenceName = "canonical_allele_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "version")
    private String version;

    @Column(name = "molecule_type")
    @Enumerated(EnumType.STRING)
    private MoleculeType moleculeType;

    @Column(name = "complexity_type")
    @Enumerated(EnumType.STRING)
    private ComplexityType complexityType;

    @Column(name = "replacement_type")
    @Enumerated(EnumType.STRING)
    private ReplacementType replacementType;

    @Column(name = "split")
    private Boolean split;

    @ManyToOne
    @JoinColumn(name = "parent_fid")
    private CanonicalAllele parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<CanonicalAllele> children;

    @OneToMany(mappedBy = "canonicalAllele", fetch = FetchType.EAGER)
    private List<SimpleAllele> relatedSimpleAlleles;

    @XmlElementWrapper(name = "identifiers")
    @XmlElement(name = "identifier")
    @ManyToMany(targetEntity = CanonicalAllele.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "canonical_allele_identifier", joinColumns = @JoinColumn(name = "canonical_allele_fid"), inverseJoinColumns = @JoinColumn(name = "identifier_fid"))
    private List<Identifier> identifiers;

    @XmlElementWrapper(name = "relatedIdentifiers")
    @XmlElement(name = "relatedIdentifier")
    @ManyToMany(targetEntity = CanonicalAllele.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "canonical_allele_related_identifier", joinColumns = @JoinColumn(name = "canonical_allele_fid"), inverseJoinColumns = @JoinColumn(name = "related_identifier_fid"))
    private List<Identifier> relatedIdentifiers;

    public CanonicalAllele() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Identifier> getIdentifiers() {
        if (identifiers == null) {
            identifiers = new ArrayList<Identifier>();
        }
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public List<Identifier> getRelatedIdentifiers() {
        if (relatedIdentifiers == null) {
            relatedIdentifiers = new ArrayList<Identifier>();
        }
        return relatedIdentifiers;
    }

    public void setRelatedIdentifiers(List<Identifier> relatedIdentifiers) {
        this.relatedIdentifiers = relatedIdentifiers;
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

    public MoleculeType getMoleculeType() {
        return moleculeType;
    }

    public void setMoleculeType(MoleculeType moleculeType) {
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

    public CanonicalAllele getParent() {
        return parent;
    }

    public void setParent(CanonicalAllele parent) {
        this.parent = parent;
    }

    public List<CanonicalAllele> getChildren() {
        if (children == null) {
            children = new ArrayList<CanonicalAllele>();
        }
        return children;
    }

    public void setChildren(List<CanonicalAllele> children) {
        this.children = children;
    }

    public List<SimpleAllele> getRelatedSimpleAlleles() {
        if (relatedSimpleAlleles == null) {
            relatedSimpleAlleles = new ArrayList<SimpleAllele>();
        }
        return relatedSimpleAlleles;
    }

    public void setRelatedSimpleAlleles(List<SimpleAllele> relatedSimpleAlleles) {
        this.relatedSimpleAlleles = relatedSimpleAlleles;
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
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
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
