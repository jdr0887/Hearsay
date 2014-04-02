package org.renci.hearsay.dao.model.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenomeRefSeq")
@XmlRootElement(name = "genomeRefSeq")
@Entity
@Table(name = "genome_ref_seq")
public class GenomeRefSeq {

    @Lob
    @Column(name = "ver_accession")
    private String verAccession;

    @Column(name = "contig")
    private String contig;

    @Column(name = "seq_type")
    private String seqType;

    @Column(name = "descr", length = 1023)
    private String descr;

    public GenomeRefSeq() {
        super();
    }

}
