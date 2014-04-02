package org.renci.hearsay.dao.model.opm;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Role")
@javax.persistence.Entity
@Table(name = "opm_role")
public class Role extends Value {

    private static final long serialVersionUID = 8309857214675278140L;
    
    @XmlValue
    @XmlSchemaType(name = "anySimpleType")
    @Transient
    private Object value;

    @XmlJavaTypeAdapter(QNameAdapter.class)
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/2001/XMLSchema-instance")
    @ManyToOne
    @JoinColumn(name = "type")
    protected QualifiedName type;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "association_fk")
    private Association association;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "end_fk")
    private End end;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "generation_fk")
    private Generation generation;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "invalidation_fk")
    private Invalidation invalidation;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "start_fk")
    private Start start;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "usage_fk")
    private Usage usage;

    public Role() {
        super();
    }

    public QualifiedName getType() {
        return type;
    }

    public void setType(QualifiedName type) {
        this.type = type;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Invalidation getInvalidation() {
        return invalidation;
    }

    public void setInvalidation(Invalidation invalidation) {
        this.invalidation = invalidation;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return String
                .format("Role [primaryKey=%s, type=%s, association=%s, end=%s, generation=%s, invalidation=%s, start=%s, usage=%s]",
                        primaryKey, type, association, end, generation, invalidation, start, usage);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((association == null) ? 0 : association.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((generation == null) ? 0 : generation.hashCode());
        result = prime * result + ((invalidation == null) ? 0 : invalidation.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((usage == null) ? 0 : usage.hashCode());
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
        Role other = (Role) obj;
        if (association == null) {
            if (other.association != null)
                return false;
        } else if (!association.equals(other.association))
            return false;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (generation == null) {
            if (other.generation != null)
                return false;
        } else if (!generation.equals(other.generation))
            return false;
        if (invalidation == null) {
            if (other.invalidation != null)
                return false;
        } else if (!invalidation.equals(other.invalidation))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (usage == null) {
            if (other.usage != null)
                return false;
        } else if (!usage.equals(other.usage))
            return false;
        return true;
    }

}
