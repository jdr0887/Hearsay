package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "gene")
@XmlType(name = "Gene")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene")
public class Gene extends BaseEntity {

    private static final long serialVersionUID = -4342595098613821909L;

    @Column(name = "hgnc_symbol")
    private String hgncSymbol;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GeneType type;

    @OneToMany(mappedBy = "gene", fetch = FetchType.EAGER)
    private Set<GeneCondition> conditions;

    @OneToMany(mappedBy = "gene", fetch = FetchType.EAGER)
    private Set<TranscriptRefSeq> transcriptRefSeqs;

    public Gene() {
        super();
    }

    public String getHgncSymbol() {
        return hgncSymbol;
    }

    public void setHgncSymbol(String hgncSymbol) {
        this.hgncSymbol = hgncSymbol;
    }

    public GeneType getType() {
        return type;
    }

    public void setType(GeneType type) {
        this.type = type;
    }

    public Set<GeneCondition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<GeneCondition> conditions) {
        this.conditions = conditions;
    }

    public Set<TranscriptRefSeq> getTranscriptRefSeqs() {
        return transcriptRefSeqs;
    }

    public void setTranscriptRefSeqs(Set<TranscriptRefSeq> transcriptRefSeqs) {
        this.transcriptRefSeqs = transcriptRefSeqs;
    }

    @Override
    public String toString() {
        return String.format("Gene [id=%s, hgncSymbol=%s, type=%s, conditions=%s, transcriptRefSeqs=%s]", id,
                hgncSymbol, type, conditions, transcriptRefSeqs);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
        result = prime * result + ((hgncSymbol == null) ? 0 : hgncSymbol.hashCode());
        result = prime * result + ((transcriptRefSeqs == null) ? 0 : transcriptRefSeqs.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Gene other = (Gene) obj;
        if (conditions == null) {
            if (other.conditions != null)
                return false;
        } else if (!conditions.equals(other.conditions))
            return false;
        if (hgncSymbol == null) {
            if (other.hgncSymbol != null)
                return false;
        } else if (!hgncSymbol.equals(other.hgncSymbol))
            return false;
        if (transcriptRefSeqs == null) {
            if (other.transcriptRefSeqs != null)
                return false;
        } else if (!transcriptRefSeqs.equals(other.transcriptRefSeqs))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
