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

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "molecularConsequence")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "molecular_consequence")
@FetchGroups({ @FetchGroup(name = "includeManyToOnes", attributes = { @FetchAttribute(name = "contextualAllele") }) })
public class MolecularConsequence implements Persistable {

    private static final long serialVersionUID = -1712420135432481226L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "molecular_consequence_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "molecular_consequence_id_seq", sequenceName = "molecular_consequence_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "so_term_id")
    private Integer soTermId;

    @ManyToOne
    @JoinColumn(name = "contextual_allele_fid")
    private ContextualAllele contextualAllele;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MolecularConsequenceType type;

    public MolecularConsequence() {
        super();
    }

    public MolecularConsequence(Integer soTermId, MolecularConsequenceType type) {
        this();
        this.soTermId = soTermId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoTermId() {
        return soTermId;
    }

    public void setSoTermId(Integer soTermId) {
        this.soTermId = soTermId;
    }

    public ContextualAllele getContextualAllele() {
        return contextualAllele;
    }

    public void setContextualAllele(ContextualAllele contextualAllele) {
        this.contextualAllele = contextualAllele;
    }

    public MolecularConsequenceType getType() {
        return type;
    }

    public void setType(MolecularConsequenceType type) {
        this.type = type;
    }

}
