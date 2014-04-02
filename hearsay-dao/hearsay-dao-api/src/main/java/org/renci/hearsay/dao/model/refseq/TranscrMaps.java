package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TranscrMaps")
@XmlRootElement(name = "transcrMaps")
@Entity
@Table(name = "transcr_maps")
public class TranscrMaps {

    @Column(name = "refseq_transcr_maps_id")
    private Long refseqTranscrMapsId;

    @Column(name = "refseq_transcr_ver_id", length = 31)
    private String refseqTranscrVerId;

    @Column(name = "genome_ref_id")
    private Integer genomeRefId;

    @Column(name = "map_count")
    private Integer mapCount;

    @Column(name = "score")
    private Float score;

    @Column(name = "ident")
    private Float ident;

    @Lob
    @Column(name = "seq_ver_accession")
    private String seqVerAccession;

    @Column(name = "exon_count")
    private Integer exonCount;

    public TranscrMaps() {
        super();
    }

}
