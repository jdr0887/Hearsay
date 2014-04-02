package org.renci.hearsay.dao.model.opm;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for Specialization complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Specialization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="specificEntity" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="generalEntity" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Specialization", propOrder = { "specificEntity", "generalEntity" })
@javax.persistence.Entity
@Table(name = "opm_specialization")
public class Specialization extends Element {

    private static final long serialVersionUID = 4308075117164896612L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "specific_entity")
    protected QualifiedName specificEntity;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "general_entity")
    protected QualifiedName generalEntity;

    public Specialization() {
        super();
    }

    public QualifiedName getSpecificEntity() {
        return specificEntity;
    }

    public void setSpecificEntity(QualifiedName specificEntity) {
        this.specificEntity = specificEntity;
    }

    public QualifiedName getGeneralEntity() {
        return generalEntity;
    }

    public void setGeneralEntity(QualifiedName generalEntity) {
        this.generalEntity = generalEntity;
    }

    @Override
    public String toString() {
        return String.format("Specialization [primaryKey=%s, specificEntity=%s, generalEntity=%s]", primaryKey,
                specificEntity, generalEntity);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((generalEntity == null) ? 0 : generalEntity.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((specificEntity == null) ? 0 : specificEntity.hashCode());
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
        Specialization other = (Specialization) obj;
        if (generalEntity == null) {
            if (other.generalEntity != null)
                return false;
        } else if (!generalEntity.equals(other.generalEntity))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (specificEntity == null) {
            if (other.specificEntity != null)
                return false;
        } else if (!specificEntity.equals(other.specificEntity))
            return false;
        return true;
    }

}
