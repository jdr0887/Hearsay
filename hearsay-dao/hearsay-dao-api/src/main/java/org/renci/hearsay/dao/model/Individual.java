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
@XmlRootElement(name = "individual")
@XmlType(name = "Individual")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "individual")
public class Individual implements Persistable {

    private static final long serialVersionUID = -247501783394999943L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individual_id_seq")
    @SequenceGenerator(name = "individual_id_seq", sequenceName = "individual_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "age_type")
    private Integer ageType;

    @Column(name = "age")
    private Integer age;

    @Column(name = "maternity_confirmed")
    private Boolean maternityConfirmed;

    @Column(name = "paternity_confirmed")
    private Boolean paternityConfirmed;

    public Individual() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAgeType() {
        return ageType;
    }

    public void setAgeType(Integer ageType) {
        this.ageType = ageType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMaternityConfirmed() {
        return maternityConfirmed;
    }

    public void setMaternityConfirmed(Boolean maternityConfirmed) {
        this.maternityConfirmed = maternityConfirmed;
    }

    public Boolean getPaternityConfirmed() {
        return paternityConfirmed;
    }

    public void setPaternityConfirmed(Boolean paternityConfirmed) {
        this.paternityConfirmed = paternityConfirmed;
    }

    @Override
    public String toString() {
        return String.format(
                "Individual [id=%s, sex=%s, ageType=%s, age=%s, maternityConfirmed=%s, paternityConfirmed=%s]", id,
                sex, ageType, age, maternityConfirmed, paternityConfirmed);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((ageType == null) ? 0 : ageType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((maternityConfirmed == null) ? 0 : maternityConfirmed.hashCode());
        result = prime * result + ((paternityConfirmed == null) ? 0 : paternityConfirmed.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
        Individual other = (Individual) obj;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (ageType == null) {
            if (other.ageType != null)
                return false;
        } else if (!ageType.equals(other.ageType))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (maternityConfirmed == null) {
            if (other.maternityConfirmed != null)
                return false;
        } else if (!maternityConfirmed.equals(other.maternityConfirmed))
            return false;
        if (paternityConfirmed == null) {
            if (other.paternityConfirmed != null)
                return false;
        } else if (!paternityConfirmed.equals(other.paternityConfirmed))
            return false;
        if (sex == null) {
            if (other.sex != null)
                return false;
        } else if (!sex.equals(other.sex))
            return false;
        return true;
    }

}
