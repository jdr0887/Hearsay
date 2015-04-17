package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class SimpleAllele implements Persistable {

    private static final long serialVersionUID = 608874481580966242L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "simple_allele_id_seq")
    @SequenceGenerator(name = "simple_allele_id_seq", sequenceName = "simple_allele_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    protected Long id;

    @Lob
    @Column(name = "allele")
    protected String allele;

    @XmlElementWrapper(name = "alleleNames")
    @XmlElement(name = "alleleName")
    @ManyToMany(targetEntity = AlleleName.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "simple_allele_allele_name", joinIndex = @Index(columnNames = { "simple_allele_fid" }))
    @JoinTable(name = "simple_allele_allele_name", joinColumns = @JoinColumn(name = "simple_allele_fid"), inverseJoinColumns = @JoinColumn(name = "allele_name_fid"))
    private Set<AlleleName> alleleNames;

    public SimpleAllele() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allele == null) ? 0 : allele.hashCode());
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
        SimpleAllele other = (SimpleAllele) obj;
        if (allele == null) {
            if (other.allele != null)
                return false;
        } else if (!allele.equals(other.allele))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
