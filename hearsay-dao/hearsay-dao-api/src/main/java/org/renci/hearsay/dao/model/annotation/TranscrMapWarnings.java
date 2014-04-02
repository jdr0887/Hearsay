package org.renci.hearsay.dao.model.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TranscrMapWarnings")
@XmlRootElement(name = "transcrMapWarnings")
@Entity
@Table(name = "transcr_map_warnings")
public class TranscrMapWarnings {

    @Column(name = "warning_name")
    private String warningName;

    public TranscrMapWarnings() {
        super();
    }

}
