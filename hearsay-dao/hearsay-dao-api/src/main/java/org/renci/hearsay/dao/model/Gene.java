package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;
import org.apache.openjpa.persistence.jdbc.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "gene")
@XmlType(propOrder = { "symbol", "chromosomes", "description", "aliases" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "gene")
@NamedQueries({ @NamedQuery(name = "Gene.findAll", query = "SELECT a FROM Gene a order by a.symbol") })
@FetchGroups({ @FetchGroup(name = "includeAll", attributes = { @FetchAttribute(name = "chromosomes"), @FetchAttribute(name = "aliases"),
        @FetchAttribute(name = "referenceSequences") }) })
public class Gene extends IdentifiableEntity {

    private static final long serialVersionUID = -5997799315221166517L;

    @XmlAttribute
    @Column(name = "symbol")
    @Index(name = "gene_symbol_idx")
    private String symbol;

    @Column(name = "description", length = 4096)
    private String description;

    @XmlElementWrapper(name = "chromosomes")
    @XmlElement(name = "chromosome")
    @ManyToMany(targetEntity = Chromosome.class)
    @JoinTable(schema = "hearsay", name = "gene_chromosome", joinColumns = @JoinColumn(name = "gene_fid") , inverseJoinColumns = @JoinColumn(name = "chromosome_fid") )
    private Set<Chromosome> chromosomes;

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("aliases")
    @XmlElementWrapper(name = "aliases")
    @XmlElement(name = "alias")
    @OneToMany(mappedBy = "gene")
    private Set<GeneSymbol> aliases;

    @XmlTransient
    @OneToMany(mappedBy = "gene")
    private Set<ReferenceSequence> referenceSequences;

    public Gene() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Chromosome> getChromosomes() {
        if (CollectionUtils.isEmpty(this.chromosomes)) {
            this.chromosomes = new HashSet<Chromosome>();
        }
        return chromosomes;
    }

    public void setChromosomes(Set<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Set<GeneSymbol> getAliases() {
        if (CollectionUtils.isEmpty(this.aliases)) {
            this.aliases = new HashSet<GeneSymbol>();
        }
        return aliases;
    }

    public void setAliases(Set<GeneSymbol> aliases) {
        this.aliases = aliases;
    }

    public Set<ReferenceSequence> getReferenceSequences() {
        if (CollectionUtils.isEmpty(this.referenceSequences)) {
            this.referenceSequences = new HashSet<ReferenceSequence>();
        }
        return referenceSequences;
    }

    public void setReferenceSequences(Set<ReferenceSequence> referenceSequences) {
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
