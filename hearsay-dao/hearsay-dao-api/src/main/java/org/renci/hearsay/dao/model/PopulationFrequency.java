package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "populationFrequency")
@XmlType(name = "PopulationFrequency")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "population_frequency")
public class PopulationFrequency extends BaseEntity {

    private static final long serialVersionUID = -1159199321634924874L;

    @ManyToOne
    @JoinColumn(name = "variant_representation_fid")
    private VariantRepresentation variantRepresentation;

    @Column(name = "population")
    private String population;

    @Column(name = "frequency")
    private Double frequency;

    @Column(name = "source")
    private String source;

    @Column(name = "version")
    private String version;

    public PopulationFrequency() {
        super();
    }

    public VariantRepresentation getVariantRepresentation() {
        return variantRepresentation;
    }

    public void setVariantRepresentation(VariantRepresentation variantRepresentation) {
        this.variantRepresentation = variantRepresentation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return String.format("PopulationFrequency [id=%s, population=%s, frequency=%s, source=%s, version=%s]", id,
                population, frequency, source, version);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
        result = prime * result + ((population == null) ? 0 : population.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
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
        PopulationFrequency other = (PopulationFrequency) obj;
        if (frequency == null) {
            if (other.frequency != null)
                return false;
        } else if (!frequency.equals(other.frequency))
            return false;
        if (population == null) {
            if (other.population != null)
                return false;
        } else if (!population.equals(other.population))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
