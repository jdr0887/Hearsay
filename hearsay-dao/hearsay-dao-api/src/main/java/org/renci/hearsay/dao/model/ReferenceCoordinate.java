package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "referenceCoordinate")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "reference_coordinate")
@FetchGroups({ @FetchGroup(name = "includeManyToOnes", attributes = { @FetchAttribute(name = "start"), @FetchAttribute(name = "end"),
        @FetchAttribute(name = "referenceSequence") }) })
public class ReferenceCoordinate extends IdentifiableEntity {

    private static final long serialVersionUID = 608874481580966242L;

    @Column(name = "ref_allele")
    private String refAllele;

    @ManyToOne
    @JoinColumn(name = "start_fid")
    private ExternalOffsetPosition start;

    @ManyToOne
    @JoinColumn(name = "end_fid")
    private ExternalOffsetPosition end;

    @ManyToOne
    @JoinColumn(name = "reference_sequence_fid")
    private ReferenceSequence referenceSequence;

    public ReferenceCoordinate() {
        super();
    }

    public String getRefAllele() {
        return refAllele;
    }

    public void setRefAllele(String refAllele) {
        this.refAllele = refAllele;
    }

    public ExternalOffsetPosition getStart() {
        return start;
    }

    public void setStart(ExternalOffsetPosition start) {
        this.start = start;
    }

    public ExternalOffsetPosition getEnd() {
        return end;
    }

    public void setEnd(ExternalOffsetPosition end) {
        this.end = end;
    }

    public ReferenceSequence getReferenceSequence() {
        return referenceSequence;
    }

    public void setReferenceSequence(ReferenceSequence referenceSequence) {
        this.referenceSequence = referenceSequence;
    }

    @Override
    public String toString() {
        return String.format("ReferenceCoordinate [id=%s, refAllele=%s]", id, refAllele);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((refAllele == null) ? 0 : refAllele.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReferenceCoordinate other = (ReferenceCoordinate) obj;
        if (refAllele == null) {
            if (other.refAllele != null)
                return false;
        } else if (!refAllele.equals(other.refAllele))
            return false;
        return true;
    }

}
