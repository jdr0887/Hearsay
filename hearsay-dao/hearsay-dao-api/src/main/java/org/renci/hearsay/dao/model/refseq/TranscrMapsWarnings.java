package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TranscrMapsWarnings")
@XmlRootElement(name = "transcrMapsWarnings")
@Entity
@Table(name = "transcr_maps_warnings")
public class TranscrMapsWarnings {

    @Column(name = "refseq_transcr_maps_id")
    private Integer refseqTranscrMapsId;

    @Column(name = "warning_warning_name")
    private String warningWarningName;

    public TranscrMapsWarnings() {
        super();
    }

}
