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
@XmlType(name = "GenomeRefSeqAltIds")
@XmlRootElement(name = "genomeRefSeqAltIds")
@Entity
@Table(name = "genome_ref_seq_alt_ids")
public class GenomeRefSeqAltIds {

    @Lob
    @Column(name = "ver_accession")
    private String verAccession;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "id")
    private String id;

    public GenomeRefSeqAltIds() {
        super();
    }

}
