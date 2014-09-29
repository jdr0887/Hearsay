package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "variantRepresentation")
@XmlType(name = "VariantRepresentation")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_representation")
public class VariantRepresentation extends BaseEntity {

    private static final long serialVersionUID = 6277477885518977679L;

    @Column(name = "hgvs")
    private String hgvs;

    @Column(name = "start")
    private Integer start;

    @Column(name = "stop")
    private Integer stop;

    @ManyToOne
    @JoinColumn(name = "canonical_variant_fid")
    private CanonicalVariant canonicalVariant;

    @OneToMany(mappedBy = "variant", fetch = FetchType.EAGER)
    private Set<MolecularConsequence> consequences;

    public VariantRepresentation() {
        super();
    }

}
