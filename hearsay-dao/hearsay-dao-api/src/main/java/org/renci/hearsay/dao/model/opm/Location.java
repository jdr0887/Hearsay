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
@XmlType(name = "Location", propOrder = {})
@javax.persistence.Entity
@Table(name = "opm_location")
public class Location extends Value {

    private static final long serialVersionUID = 4363715241382468954L;

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
    @JoinColumn(name = "activity_fk")
    private Activity activity;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "agent_fk")
    private Agent agent;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "end_fk")
    private End end;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "entity_fk")
    private Entity entity;

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

    public Location() {
        super();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public QualifiedName getType() {
        return type;
    }

    public void setType(QualifiedName type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String
                .format("Location [primaryKey=%s, value=%s, type=%s, activity=%s, agent=%s, end=%s, entity=%s, generation=%s, invalidation=%s, start=%s, usage=%s]",
                        primaryKey, value, type, activity, agent, end, entity, generation, invalidation, start, usage);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((agent == null) ? 0 : agent.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((generation == null) ? 0 : generation.hashCode());
        result = prime * result + ((invalidation == null) ? 0 : invalidation.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((usage == null) ? 0 : usage.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Location other = (Location) obj;
        if (activity == null) {
            if (other.activity != null)
                return false;
        } else if (!activity.equals(other.activity))
            return false;
        if (agent == null) {
            if (other.agent != null)
                return false;
        } else if (!agent.equals(other.agent))
            return false;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
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
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}
