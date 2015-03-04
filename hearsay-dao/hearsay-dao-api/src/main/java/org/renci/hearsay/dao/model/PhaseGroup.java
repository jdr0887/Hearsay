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
@XmlRootElement(name = "phaseGroup")
@XmlType(name = "PhaseGroup")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "phase_group")
public class PhaseGroup implements Persistable {

    private static final long serialVersionUID = -247501783394999943L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phase_group_id_seq")
    @SequenceGenerator(name = "phase_group_id_seq", sequenceName = "phase_group_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    private String phasing;

    public PhaseGroup() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhasing() {
        return phasing;
    }

    public void setPhasing(String phasing) {
        this.phasing = phasing;
    }

    @Override
    public String toString() {
        return String.format("PhaseGroup [id=%s, phasing=%s]", id, phasing);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((phasing == null) ? 0 : phasing.hashCode());
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
        PhaseGroup other = (PhaseGroup) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (phasing == null) {
            if (other.phasing != null)
                return false;
        } else if (!phasing.equals(other.phasing))
            return false;
        return true;
    }

}
