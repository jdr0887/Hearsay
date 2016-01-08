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

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "intronOffset")
@XmlType(propOrder = { "id", "start", "end", "strandType" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "intron_offset")
public class IntronOffset implements Persistable {

    private static final long serialVersionUID = -4320424521732996608L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intron_offset_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "intron_offset_id_seq", sequenceName = "intron_offset_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "start")
    private Integer start;

    @Column(name = "stop")
    private Integer stop;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    public IntronOffset() {
        super();
    }

    public IntronOffset(Integer start, Integer stop, StrandType strandType) {
        this();
        this.start = start;
        this.stop = stop;
        this.strandType = strandType;
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

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
    }

    @Override
    public String toString() {
        return String.format("IntronOffset [id=%s, start=%s, stop=%s, strandType=%s]", id, start, stop, strandType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((strandType == null) ? 0 : strandType.hashCode());
        result = prime * result + ((stop == null) ? 0 : stop.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
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
        IntronOffset other = (IntronOffset) obj;
        if (strandType != other.strandType)
            return false;
        if (stop == null) {
            if (other.stop != null)
                return false;
        } else if (!stop.equals(other.stop))
            return false;
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
        return true;
    }

}
