package org.renci.hearsay.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "gene")
@XmlType(propOrder = { "symbol", "chromosomes", "description", "aliases" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "gene", indexes = { @Index(columnList = "symbol") })
@NamedQueries({ @NamedQuery(name = "Gene.findAll", query = "FROM Gene a order by a.symbol") })
public class Gene extends IdentifiableEntity {

    private static final long serialVersionUID = -5997799315221166517L;

    @XmlAttribute
    @Column(name = "symbol")
    private String symbol;

    @XmlElementWrapper(name = "chromosomes")
    @XmlElement(name = "chromosome")
    @ManyToMany(targetEntity = Chromosome.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(schema = "hearsay", name = "gene_chromosome", joinColumns = @JoinColumn(name = "gene_fid") , inverseJoinColumns = @JoinColumn(name = "chromosome_fid") )
    private List<Chromosome> chromosomes;

    @Column(name = "description", length = 4096)
    private String description;

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("aliases")
    @XmlElementWrapper(name = "aliases")
    @XmlElement(name = "alias")
    @OneToMany(mappedBy = "gene", fetch = FetchType.EAGER)
    private List<GeneSymbol> aliases;

    @XmlTransient
    @OneToMany(mappedBy = "gene", fetch = FetchType.LAZY)
    private List<ReferenceSequence> referenceSequences;

    public Gene() {
        super();
        this.aliases = new ArrayList<GeneSymbol>();
        this.chromosomes = new ArrayList<Chromosome>();
        this.referenceSequences = new ArrayList<ReferenceSequence>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<GeneSymbol> getAliases() {
        return aliases;
    }

    public void setAliases(List<GeneSymbol> aliases) {
        this.aliases = aliases;
    }

    public List<ReferenceSequence> getReferenceSequences() {
        return referenceSequences;
    }

    public void setReferenceSequences(List<ReferenceSequence> referenceSequences) {
        this.referenceSequences = referenceSequences;
    }

    @Override
    public String toString() {
        return String.format("Gene [id=%s, symbol=%s, description=%s]", id, symbol, description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
        Gene other = (Gene) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        return true;
    }

}
