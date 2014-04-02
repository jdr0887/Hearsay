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
@XmlType(name = "GenomeRefSeqs")
@XmlRootElement(name = "genomeRefSeqs")
@Entity
@Table(name = "genome_ref_seqs")
public class GenomeRefSeqs {

    @Column(name = "ref_id")
    private Integer refId;

    @Lob
    @Column(name = "seq_ver_accession")
    private String seqVerAccession;

    public GenomeRefSeqs() {
        super();
    }

}
