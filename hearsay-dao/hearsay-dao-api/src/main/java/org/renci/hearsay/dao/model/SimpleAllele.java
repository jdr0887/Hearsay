package org.renci.hearsay.dao.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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

import org.apache.openjpa.persistence.jdbc.ContainerTable;
import org.apache.openjpa.persistence.jdbc.Index;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "simpleAllele")
@XmlType(name = "SimpleAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_allele")
public class SimpleAllele implements Persistable {

    private static final long serialVersionUID = 608874481580966242L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "simple_allele_id_seq")
    @SequenceGenerator(name = "simple_allele_id_seq", sequenceName = "simple_allele_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @XmlElementWrapper(name = "identifiers")
    @XmlElement(name = "identifier")
    @ManyToMany(targetEntity = CanonicalAllele.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "simple_allele_identifier", joinColumns = @JoinColumn(name = "simple_allele_fid"), inverseJoinColumns = @JoinColumn(name = "identifier_fid"))
    private List<Identifier> identifiers;

    @ManyToOne
    @JoinColumn(name = "canonical_allele_fid")
    private CanonicalAllele canonicalAllele;

    @Column(name = "allele")
    private String allele;

    @XmlElementWrapper(name = "alleleNames")
    @XmlElement(name = "alleleName")
    @ManyToMany(targetEntity = AlleleName.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "simple_allele_allele_name", joinIndex = @Index(columnNames = { "simple_allele_fid" }))
    @JoinTable(name = "simple_allele_allele_name", joinColumns = @JoinColumn(name = "simple_allele_fid"), inverseJoinColumns = @JoinColumn(name = "allele_name_fid"))
    private Set<AlleleName> alleleNames;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SimpleAlleleType type;

    @Column(name = "primary_nucleotide_change_type")
    @Enumerated(EnumType.STRING)
    private MutationChangeType primaryNucleotideChangeType;

    @Column(name = "ancillary_nucleotide_change_type")
    @Enumerated(EnumType.STRING)
    private MutationChangeType ancillaryNucleotideChangeType;

    @Column(name = "primary_amino_acid_change_type")
    @Enumerated(EnumType.STRING)
    private MutationChangeType primaryAminoAcidChangeType;

    @Column(name = "ancillary_amino_acid_change_type")
    @Enumerated(EnumType.STRING)
    private MutationChangeType ancillaryAminoAcidChangeType;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
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

    public Set<AlleleName> getAlleleNames() {
        return alleleNames;
    }

    public void setAlleleNames(Set<AlleleName> alleleNames) {
        this.alleleNames = alleleNames;
    }

    public SimpleAlleleType getType() {
        return type;
    }

    public void setType(SimpleAlleleType type) {
        this.type = type;
    }

    public MutationChangeType getPrimaryNucleotideChangeType() {
        return primaryNucleotideChangeType;
    }

    public void setPrimaryNucleotideChangeType(MutationChangeType primaryNucleotideChangeType) {
        this.primaryNucleotideChangeType = primaryNucleotideChangeType;
    }

    public MutationChangeType getAncillaryNucleotideChangeType() {
        return ancillaryNucleotideChangeType;
    }

    public void setAncillaryNucleotideChangeType(MutationChangeType ancillaryNucleotideChangeType) {
        this.ancillaryNucleotideChangeType = ancillaryNucleotideChangeType;
    }

    public MutationChangeType getPrimaryAminoAcidChangeType() {
        return primaryAminoAcidChangeType;
    }

    public void setPrimaryAminoAcidChangeType(MutationChangeType primaryAminoAcidChangeType) {
        this.primaryAminoAcidChangeType = primaryAminoAcidChangeType;
    }

    public MutationChangeType getAncillaryAminoAcidChangeType() {
        return ancillaryAminoAcidChangeType;
    }

    public void setAncillaryAminoAcidChangeType(MutationChangeType ancillaryAminoAcidChangeType) {
        this.ancillaryAminoAcidChangeType = ancillaryAminoAcidChangeType;
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
        return String
                .format("SimpleAllele [id=%s, allele=%s, type=%s, primaryNucleotideChangeType=%s, ancillaryNucleotideChangeType=%s, primaryAminoAcidChangeType=%s, ancillaryAminoAcidChangeType=%s, referenceCoordinate=%s]",
                        id, allele, type, primaryNucleotideChangeType, ancillaryNucleotideChangeType,
                        primaryAminoAcidChangeType, ancillaryAminoAcidChangeType, referenceCoordinate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allele == null) ? 0 : allele.hashCode());
        result = prime * result
                + ((ancillaryAminoAcidChangeType == null) ? 0 : ancillaryAminoAcidChangeType.hashCode());
        result = prime * result
                + ((ancillaryNucleotideChangeType == null) ? 0 : ancillaryNucleotideChangeType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((primaryAminoAcidChangeType == null) ? 0 : primaryAminoAcidChangeType.hashCode());
        result = prime * result + ((primaryNucleotideChangeType == null) ? 0 : primaryNucleotideChangeType.hashCode());
        result = prime * result + ((referenceCoordinate == null) ? 0 : referenceCoordinate.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        SimpleAllele other = (SimpleAllele) obj;
        if (allele == null) {
            if (other.allele != null)
                return false;
        } else if (!allele.equals(other.allele))
            return false;
        if (ancillaryAminoAcidChangeType != other.ancillaryAminoAcidChangeType)
            return false;
        if (ancillaryNucleotideChangeType != other.ancillaryNucleotideChangeType)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (primaryAminoAcidChangeType != other.primaryAminoAcidChangeType)
            return false;
        if (primaryNucleotideChangeType != other.primaryNucleotideChangeType)
            return false;
        if (referenceCoordinate == null) {
            if (other.referenceCoordinate != null)
                return false;
        } else if (!referenceCoordinate.equals(other.referenceCoordinate))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
