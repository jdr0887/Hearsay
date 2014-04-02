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
 * Java class for Mention complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Mention">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="specificEntity" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="generalEntity" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="bundle" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mention", propOrder = { "specificEntity", "generalEntity", "bundle" })
@javax.persistence.Entity
@Table(name = "opm_mention")
public class Mention extends Element {

    private static final long serialVersionUID = 8393580428329325005L;

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

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "bundle")
    protected QualifiedName bundle;

    public Mention() {
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

    public QualifiedName getBundle() {
        return bundle;
    }

    public void setBundle(QualifiedName bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        return String.format("Mention [primaryKey=%s, specificEntity=%s, generalEntity=%s, bundle=%s]", primaryKey,
                specificEntity, generalEntity, bundle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bundle == null) ? 0 : bundle.hashCode());
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
        Mention other = (Mention) obj;
        if (bundle == null) {
            if (other.bundle != null)
                return false;
        } else if (!bundle.equals(other.bundle))
            return false;
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
