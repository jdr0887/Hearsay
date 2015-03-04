package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@XmlRootElement(name = "geneCondition")
@XmlType(name = "GeneCondition")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene_condition")
public class GeneCondition implements Persistable {

    private static final long serialVersionUID = -1407404805678953995L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gene_condition_id_seq")
    @SequenceGenerator(name = "gene_condition_id_seq", sequenceName = "gene_condition_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @ManyToOne
    @JoinColumn(name = "resolved_condition_fid")
    private ResolvedCondition resolvedCondition;

    @OneToMany(mappedBy = "condition", fetch = FetchType.LAZY)
    private Set<GeneConditionAssertion> assertions;

    public GeneCondition() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public ResolvedCondition getResolvedCondition() {
        return resolvedCondition;
    }

    public void setResolvedCondition(ResolvedCondition resolvedCondition) {
        this.resolvedCondition = resolvedCondition;
    }

    public Set<GeneConditionAssertion> getAssertions() {
        return assertions;
    }

    public void setAssertions(Set<GeneConditionAssertion> assertions) {
        this.assertions = assertions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        GeneCondition other = (GeneCondition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
