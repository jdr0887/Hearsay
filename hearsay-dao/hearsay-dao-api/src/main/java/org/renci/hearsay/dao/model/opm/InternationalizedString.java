package org.renci.hearsay.dao.model.opm;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for InternationalizedString complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternationalizedString">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalizedString", propOrder = { "value" })
@javax.persistence.Entity
@Table(name = "opm_internationalized_string")
public class InternationalizedString extends Element {

    private static final long serialVersionUID = 6122311693829692680L;

    @XmlValue
    @Column(name = "value")
    protected String value;

    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @Column(name = "lang")
    protected String lang;

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
    @JoinColumn(name = "association_fk")
    private Association association;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "attribution_fk")
    private Attribution attribution;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "communication_fk")
    private Communication communication;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "delegation_fk")
    private Delegation delegation;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "derivation_fk")
    private Derivation derivation;

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
    @JoinColumn(name = "influence_fk")
    private Influence influence;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "insertion_fk")
    private Insertion insertion;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "invalidation_fk")
    private Invalidation invalidation;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "removal_fk")
    private Removal removal;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "start_fk")
    private Start start;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "usage_fk")
    private Usage usage;

    public InternationalizedString() {
        super();
    }

    public InternationalizedString(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
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

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public Attribution getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public Derivation getDerivation() {
        return derivation;
    }

    public void setDerivation(Derivation derivation) {
        this.derivation = derivation;
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

    public Influence getInfluence() {
        return influence;
    }

    public void setInfluence(Influence influence) {
        this.influence = influence;
    }

    public Insertion getInsertion() {
        return insertion;
    }

    public void setInsertion(Insertion insertion) {
        this.insertion = insertion;
    }

    public Invalidation getInvalidation() {
        return invalidation;
    }

    public void setInvalidation(Invalidation invalidation) {
        this.invalidation = invalidation;
    }

    public Removal getRemoval() {
        return removal;
    }

    public void setRemoval(Removal removal) {
        this.removal = removal;
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
                .format("InternationalizedString [primaryKey=%s, value=%s, lang=%s, activity=%s, agent=%s, association=%s, attribution=%s, communication=%s, delegation=%s, derivation=%s, end=%s, entity=%s, generation=%s, influence=%s, insertion=%s, invalidation=%s, removal=%s, start=%s, usage=%s]",
                        primaryKey, value, lang, activity, agent, association, attribution, communication, delegation,
                        derivation, end, entity, generation, influence, insertion, invalidation, removal, start, usage);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((agent == null) ? 0 : agent.hashCode());
        result = prime * result + ((association == null) ? 0 : association.hashCode());
        result = prime * result + ((attribution == null) ? 0 : attribution.hashCode());
        result = prime * result + ((communication == null) ? 0 : communication.hashCode());
        result = prime * result + ((delegation == null) ? 0 : delegation.hashCode());
        result = prime * result + ((derivation == null) ? 0 : derivation.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((generation == null) ? 0 : generation.hashCode());
        result = prime * result + ((influence == null) ? 0 : influence.hashCode());
        result = prime * result + ((insertion == null) ? 0 : insertion.hashCode());
        result = prime * result + ((invalidation == null) ? 0 : invalidation.hashCode());
        result = prime * result + ((lang == null) ? 0 : lang.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((removal == null) ? 0 : removal.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
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
        InternationalizedString other = (InternationalizedString) obj;
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
        if (association == null) {
            if (other.association != null)
                return false;
        } else if (!association.equals(other.association))
            return false;
        if (attribution == null) {
            if (other.attribution != null)
                return false;
        } else if (!attribution.equals(other.attribution))
            return false;
        if (communication == null) {
            if (other.communication != null)
                return false;
        } else if (!communication.equals(other.communication))
            return false;
        if (delegation == null) {
            if (other.delegation != null)
                return false;
        } else if (!delegation.equals(other.delegation))
            return false;
        if (derivation == null) {
            if (other.derivation != null)
                return false;
        } else if (!derivation.equals(other.derivation))
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
        if (influence == null) {
            if (other.influence != null)
                return false;
        } else if (!influence.equals(other.influence))
            return false;
        if (insertion == null) {
            if (other.insertion != null)
                return false;
        } else if (!insertion.equals(other.insertion))
            return false;
        if (invalidation == null) {
            if (other.invalidation != null)
                return false;
        } else if (!invalidation.equals(other.invalidation))
            return false;
        if (lang == null) {
            if (other.lang != null)
                return false;
        } else if (!lang.equals(other.lang))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (removal == null) {
            if (other.removal != null)
                return false;
        } else if (!removal.equals(other.removal))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
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
