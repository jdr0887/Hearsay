package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

    @Column(name = "project_name")
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "lab_name")
    private Lab lab;

}
