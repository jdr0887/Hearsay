package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lab")
public class Lab {

    @Column(name = "lab_name")
    private String labName;

}
