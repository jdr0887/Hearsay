package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "individualVariant")
@XmlType(name = "IndividualVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "individual_variant")
public class IndividualVariant implements Persistable {

    private static final long serialVersionUID = -247501783394999943L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individual_variant_id_seq")
    @SequenceGenerator(name = "individual_variant_id_seq", sequenceName = "individual_variant_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        int result = 1;
        result = prime * result + ((allelicState == null) ? 0 : allelicState.hashCode());
        result = prime * result + ((deNovo == null) ? 0 : deNovo.hashCode());
        result = prime * result + ((exist == null) ? 0 : exist.hashCode());
        result = prime * result + ((genomicSource == null) ? 0 : genomicSource.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
