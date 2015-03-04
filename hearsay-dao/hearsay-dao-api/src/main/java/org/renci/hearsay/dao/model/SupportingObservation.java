package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@XmlRootElement(name = "supportingObservation")
@XmlType(name = "SupportingObservation")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "supporting_observation")
public class SupportingObservation implements Persistable {

    private static final long serialVersionUID = -8220489659799273363L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supporting_observation_id_seq")
    @SequenceGenerator(name = "supporting_observation_id_seq", sequenceName = "supporting_observation_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "geophraphic_origin")
    private Integer geophraphicOrigin;

    @Column(name = "ethnicity")
    private Integer ethnicity;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "allele_origin")
    private Integer alleleOrigin;

    public SupportingObservation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGeophraphicOrigin() {
        return geophraphicOrigin;
    }

    public void setGeophraphicOrigin(Integer geophraphicOrigin) {
        this.geophraphicOrigin = geophraphicOrigin;
    }

    public Integer getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Integer ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Integer getAlleleOrigin() {
        return alleleOrigin;
    }

    public void setAlleleOrigin(Integer alleleOrigin) {
        this.alleleOrigin = alleleOrigin;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format(
                "SupportingObservation [id=%s, geophraphicOrigin=%s, ethnicity=%s, gender=%s, alleleOrigin=%s]", id,
                geophraphicOrigin, ethnicity, gender, alleleOrigin);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alleleOrigin == null) ? 0 : alleleOrigin.hashCode());
        result = prime * result + ((ethnicity == null) ? 0 : ethnicity.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((geophraphicOrigin == null) ? 0 : geophraphicOrigin.hashCode());
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
        SupportingObservation other = (SupportingObservation) obj;
        if (alleleOrigin == null) {
            if (other.alleleOrigin != null)
                return false;
        } else if (!alleleOrigin.equals(other.alleleOrigin))
            return false;
        if (ethnicity == null) {
            if (other.ethnicity != null)
                return false;
        } else if (!ethnicity.equals(other.ethnicity))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (geophraphicOrigin == null) {
            if (other.geophraphicOrigin != null)
                return false;
        } else if (!geophraphicOrigin.equals(other.geophraphicOrigin))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
