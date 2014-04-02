package org.renci.hearsay.dao.model.var;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VarSetLoad {

    @ManyToOne
    @JoinColumn(name = "var_set_id")
    private VarSet varSet;

    @Column(name = "load_filename")
    private String loadFilename;

    @Column(name = "load_user")
    private String loadUser;

    @Column(name = "load_time_start")
    private Date loadTimeStart;

    @Column(name = "load_time_stop")
    private Date loadTimeStop;

    @Column(name = "notes", length = 1024)
    private String notes;

}
