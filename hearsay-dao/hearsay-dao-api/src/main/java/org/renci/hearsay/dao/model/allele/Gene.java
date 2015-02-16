package org.renci.hearsay.dao.model.allele;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "gene")
@XmlType(name = "Gene")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene")
@NamedQueries({ @NamedQuery(name = "Gene.findAll", query = "SELECT a FROM Gene a order by a.name") })
public class Gene extends BaseEntity {

    private static final long serialVersionUID = -5997799315221166517L;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return String.format("Gene [id=%s, name=%s, description=%s, symbol=%s, aliasSymbol=%s]", id, name, description,
                symbol, aliasSymbol);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((aliasSymbol == null) ? 0 : aliasSymbol.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
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
