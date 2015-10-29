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

import org.apache.commons.lang3.Range;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlType(propOrder = { "id", "start", "stop" })
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "location")
public class Location implements Persistable {

    private static final long serialVersionUID = 3183366690568806100L;

    @XmlAttribute
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "location_id_seq", sequenceName = "location_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @XmlAttribute
    @Column(name = "start")
    private Integer start;

    @XmlAttribute
    @Column(name = "stop")
    private Integer stop;

    public Location() {
        super();
    }

    public Location(Integer start, Integer stop) {
        super();
        this.start = start;
        this.stop = stop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public Integer diff() {
        if (this.start > this.stop) {
            return this.start - this.stop;
        } else {
            return this.stop - this.start;
        }
    }

    public Range<Integer> toRange() {
        if (this.start > this.stop) {
            return Range.between(this.start, this.stop);
        } else {
            return Range.between(this.stop, this.start);
        }
    }

    @Override
    public String toString() {
        return String.format("Location [id=%s, start=%s, stop=%s]", id, start, stop);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((stop == null) ? 0 : stop.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (stop == null) {
            if (other.stop != null)
                return false;
        } else if (!stop.equals(other.stop))
            return false;
        return true;
    }

}
