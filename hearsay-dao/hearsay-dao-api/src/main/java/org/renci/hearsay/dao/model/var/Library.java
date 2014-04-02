package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "library")
public class Library {

    @Column(name = "library_id")
    private Long library_id;

    @Column(name = "htsf_library_name")
    private String htsfLibraryName;

    @Column(name = "sample_id")
    private Sample sample;

}
