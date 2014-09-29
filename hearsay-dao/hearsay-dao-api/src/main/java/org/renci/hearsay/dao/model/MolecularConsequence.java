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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "molecularConsequence")
@XmlType(name = "MolecularConsequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "molecular_consequence")
public class MolecularConsequence extends BaseEntity {

    private static final long serialVersionUID = -1712420135432481226L;

    @Column(name = "sequence_ontology_term")
    private String sequenceOntologyTerm;

    @ManyToOne
    @JoinColumn(name = "variant_fid")
    private VariantRepresentation variant;

    public MolecularConsequence() {
        super();
    }

}
