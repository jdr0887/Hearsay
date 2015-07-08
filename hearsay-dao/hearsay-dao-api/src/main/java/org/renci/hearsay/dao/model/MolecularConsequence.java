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

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "molecularConsequence")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "molecular_consequence")
public class MolecularConsequence implements Persistable {

    private static final long serialVersionUID = -1712420135432481226L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "molecular_consequence_id_seq")
    @SequenceGenerator(name = "molecular_consequence_id_seq", sequenceName = "molecular_consequence_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "so_term_id")
    private Integer soTermId;

    @ManyToOne
    @JoinColumn(name = "simple_allele_fid")
    private SimpleAllele simpleAllele;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private MolecularConsequenceType type;

    public MolecularConsequence() {
        super();
    }

    public MolecularConsequence(Integer soTermId, MolecularConsequenceType type) {
        super();
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

    public SimpleAllele getSimpleAllele() {
        return simpleAllele;
    }

    public void setSimpleAllele(SimpleAllele simpleAllele) {
        this.simpleAllele = simpleAllele;
    }

    public MolecularConsequenceType getType() {
        return type;
    }

    public void setType(MolecularConsequenceType type) {
        this.type = type;
    }

}
