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

import org.apache.openjpa.persistence.jdbc.Index;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "contextualAlleleName")
@XmlType(propOrder = { "id", "type", "name", "legacy", "preferred", "contextualAllele" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "contextual_allele_name")
public class ContextualAlleleName implements Persistable {

    private static final long serialVersionUID = 1701406094966941523L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contextual_allele_name_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "contextual_allele_name_id_seq", sequenceName = "contextual_allele_name_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ContextualAlleleNameType type;

    @Column(name = "name")
    @Index(name = "contextual_allele_name_name_idx")
    private String name;

    @Column(name = "legacy")
    private Boolean legacy;

    @Column(name = "preferred")
    private Boolean preferred;

    public ContextualAlleleName() {
        super();
    }

    public ContextualAlleleName(String name, ContextualAlleleNameType type) {
        super();
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLegacy() {
        return legacy;
    }

    public void setLegacy(Boolean legacy) {
        this.legacy = legacy;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public ContextualAlleleNameType getType() {
        return type;
    }

    public void setType(ContextualAlleleNameType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("ContextualAlleleName [id=%s, type=%s, name=%s, legacy=%s, preferred=%s]", id, type, name, legacy, preferred);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((legacy == null) ? 0 : legacy.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((preferred == null) ? 0 : preferred.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        ContextualAlleleName other = (ContextualAlleleName) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (legacy == null) {
            if (other.legacy != null)
                return false;
        } else if (!legacy.equals(other.legacy))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (preferred == null) {
            if (other.preferred != null)
                return false;
        } else if (!preferred.equals(other.preferred))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
