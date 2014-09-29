package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "individualVariant")
@XmlType(name = "IndividualVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "individual_variant")
public class IndividualVariant extends BaseEntity {

    private static final long serialVersionUID = -247501783394999943L;

    @Column(name = "genomic_source")
    private Integer genomicSource;

    @Column(name = "allelic_state")
    private Integer allelicState;

    @Column(name = "exist")
    private Boolean exist;

    @Enumerated(EnumType.STRING)
    @Column(name = "de_novo")
    private DeNovoType deNovo;

    public IndividualVariant() {
        super();
    }

    public Integer getGenomicSource() {
        return genomicSource;
    }

    public void setGenomicSource(Integer genomicSource) {
        this.genomicSource = genomicSource;
    }

    public Integer getAllelicState() {
        return allelicState;
    }

    public void setAllelicState(Integer allelicState) {
        this.allelicState = allelicState;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public DeNovoType getDeNovo() {
        return deNovo;
    }

    public void setDeNovo(DeNovoType deNovo) {
        this.deNovo = deNovo;
    }

    @Override
    public String toString() {
        return String.format("IndividualVariant [id=%s, genomicSource=%s, allelicState=%s, exist=%s, deNovo=%s]", id,
                genomicSource, allelicState, exist, deNovo);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((allelicState == null) ? 0 : allelicState.hashCode());
        result = prime * result + ((deNovo == null) ? 0 : deNovo.hashCode());
        result = prime * result + ((exist == null) ? 0 : exist.hashCode());
        result = prime * result + ((genomicSource == null) ? 0 : genomicSource.hashCode());
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
        IndividualVariant other = (IndividualVariant) obj;
        if (allelicState == null) {
            if (other.allelicState != null)
                return false;
        } else if (!allelicState.equals(other.allelicState))
            return false;
        if (deNovo != other.deNovo)
            return false;
        if (exist == null) {
            if (other.exist != null)
                return false;
        } else if (!exist.equals(other.exist))
            return false;
        if (genomicSource == null) {
            if (other.genomicSource != null)
                return false;
        } else if (!genomicSource.equals(other.genomicSource))
            return false;
        return true;
    }

}
