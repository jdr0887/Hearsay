package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sample")
public class Sample {

    @Column(name = "sample_id")
    private Long sampleId;

    @ManyToOne
    @JoinColumn(name = "project_name")
    private Project project;

    @Column(name = "sample_name")
    private String sampleName;
}
