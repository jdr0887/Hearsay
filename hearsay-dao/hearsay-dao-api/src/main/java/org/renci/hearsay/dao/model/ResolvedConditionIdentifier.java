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
@XmlRootElement(name = "resolvedConditionIdentifier")
@XmlType(name = "ResolvedConditionIdentifier")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "resolved_condition_identifier")
public class ResolvedConditionIdentifier extends BaseEntity {

    private static final long serialVersionUID = -1856971652669997219L;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "resolved_condition_fid")
    private ResolvedCondition resolvedCondition;

    public ResolvedConditionIdentifier() {
        super();
    }

}
