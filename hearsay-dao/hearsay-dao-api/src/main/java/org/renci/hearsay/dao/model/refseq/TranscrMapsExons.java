package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TranscrMapsExons")
@XmlRootElement(name = "transcrMapsExons")
@Entity
@Table(name = "transcr_maps_exons")
public class TranscrMapsExons {

    @Column(name = "refseq_transcr_maps_id")
    private Integer refseqTranscrMapsId;

    @Column(name = "exon_num")
    private Integer exonNum;

    @Column(name = "transcr_start")
    private Integer transcrStart;

    @Column(name = "transcr_end")
    private Integer transcrEnd;

    @Column(name = "contig_start")
    private Integer contigStart;

    @Column(name = "contig_end")
    private Integer contigEnd;

    public TranscrMapsExons() {
        super();
    }

}
