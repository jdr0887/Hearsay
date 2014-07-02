package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@XmlRootElement(name = "transcript")
@XmlType(name = "Transcript")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript")
@NamedQueries({ @NamedQuery(name = "Transcript.findAll", query = "SELECT a FROM Transcript a") })
public class Transcript extends BaseEntity {

    private static final long serialVersionUID = 3621851350760327992L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @Column(name = "accession")
    private String accession;

    @OneToMany(mappedBy = "transcript", fetch = FetchType.EAGER)
    private Set<MappedTranscript> mappedTranscripts;

    @OneToMany(mappedBy = "transcript", fetch = FetchType.EAGER)
    private Set<Translation> translations;

    public Transcript() {
        super();
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public Set<MappedTranscript> getMappedTranscripts() {
        if (mappedTranscripts == null) {
            mappedTranscripts = new HashSet<MappedTranscript>();
        }
        return mappedTranscripts;
    }

    public void setMappedTranscripts(Set<MappedTranscript> mappedTranscripts) {
        this.mappedTranscripts = mappedTranscripts;
    }

    public Set<Translation> getTranslations() {
        if (translations == null) {
            translations = new HashSet<Translation>();
        }
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return String.format("Transcript [accession=%s, id=%s]", accession, id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
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
        Transcript other = (Transcript) obj;
        if (accession == null) {
            if (other.accession != null)
                return false;
        } else if (!accession.equals(other.accession))
            return false;
        return true;
    }

}
