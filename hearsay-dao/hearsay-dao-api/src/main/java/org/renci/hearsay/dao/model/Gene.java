package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.Index;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "gene")
@XmlType(name = "Gene")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene")
@NamedQueries({ @NamedQuery(name = "Gene.findAll", query = "SELECT a FROM Gene a order by a.name") })
public class Gene implements Persistable {

    private static final long serialVersionUID = -5997799315221166517L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gene_id_seq")
    @SequenceGenerator(name = "gene_id_seq", sequenceName = "gene_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Index
    @Column(name = "name")
    private String name;

    @Index
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "alias_symbol")
    private String aliasSymbol;

    @OneToMany(mappedBy = "gene", fetch = FetchType.LAZY)
    private Set<GeneReferenceSequence> geneReferenceSequences;

    @OneToMany(mappedBy = "gene", fetch = FetchType.LAZY)
    private Set<TranscriptReferenceSequence> transcriptReferenceSequences;

    public Gene() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAliasSymbol() {
        return aliasSymbol;
    }

    public void setAliasSymbol(String aliasSymbol) {
        this.aliasSymbol = aliasSymbol;
    }

    public Set<GeneReferenceSequence> getGeneReferenceSequences() {
        return geneReferenceSequences;
    }

    public void setGeneReferenceSequences(Set<GeneReferenceSequence> geneReferenceSequences) {
        this.geneReferenceSequences = geneReferenceSequences;
    }

    public Set<TranscriptReferenceSequence> getTranscriptReferenceSequences() {
        return transcriptReferenceSequences;
    }

    public void setTranscriptReferenceSequences(Set<TranscriptReferenceSequence> transcriptReferenceSequences) {
        this.transcriptReferenceSequences = transcriptReferenceSequences;
    }

    @Override
    public String toString() {
        return String.format("Gene [id=%s, name=%s, symbol=%s, aliasSymbol=%s]", id, name, symbol, aliasSymbol);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((aliasSymbol == null) ? 0 : aliasSymbol.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
        Gene other = (Gene) obj;
        if (aliasSymbol == null) {
            if (other.aliasSymbol != null)
                return false;
        } else if (!aliasSymbol.equals(other.aliasSymbol))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        return true;
    }

}
