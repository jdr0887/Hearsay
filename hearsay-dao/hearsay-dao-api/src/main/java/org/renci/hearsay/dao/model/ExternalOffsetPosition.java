package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "externalOffsetPosition")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "external_offset_position")
@FetchGroups({ @FetchGroup(name = "includeManyToOnes", attributes = {}) })
public class ExternalOffsetPosition implements Persistable {

    private static final long serialVersionUID = -224146294779351437L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "external_offset_position_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "external_offset_position_id_seq", sequenceName = "external_offset_position_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction")
    private DirectionType direction;

    @Column(name = "index")
    private Integer index;

    @Column(name = "length")
    private Integer length;

    @Column(name = "genomic_position_index")
    private Integer genomicPositionIndex;

    @ManyToOne
    @JoinColumn(name = "genome_reference_sequence_fid")
    private Identifier genomicReferenceSequence;

    public ExternalOffsetPosition() {
        super();
    }

    public ExternalOffsetPosition(Integer index) {
        super();
        this.index = index;
    }

    public ExternalOffsetPosition(DirectionType direction, Integer index, Integer length) {
        super();
        this.direction = direction;
        this.index = index;
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getGenomicPositionIndex() {
        return genomicPositionIndex;
    }

    public void setGenomicPositionIndex(Integer genomicPositionIndex) {
        this.genomicPositionIndex = genomicPositionIndex;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Identifier getGenomicReferenceSequence() {
        return genomicReferenceSequence;
    }

    public void setGenomicReferenceSequence(Identifier genomicReferenceSequence) {
        this.genomicReferenceSequence = genomicReferenceSequence;
    }

    @Override
    public String toString() {
        return String.format("ExternalOffsetPosition [id=%s, direction=%s, index=%s, length=%s, genomicPositionIndex=%s]", id, direction,
                index, length, genomicPositionIndex);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((genomicPositionIndex == null) ? 0 : genomicPositionIndex.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        result = prime * result + ((length == null) ? 0 : length.hashCode());
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
        ExternalOffsetPosition other = (ExternalOffsetPosition) obj;
        if (direction != other.direction)
            return false;
        if (genomicPositionIndex == null) {
            if (other.genomicPositionIndex != null)
                return false;
        } else if (!genomicPositionIndex.equals(other.genomicPositionIndex))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (index == null) {
            if (other.index != null)
                return false;
        } else if (!index.equals(other.index))
            return false;
        if (length == null) {
            if (other.length != null)
                return false;
        } else if (!length.equals(other.length))
            return false;
        return true;
    }

}
