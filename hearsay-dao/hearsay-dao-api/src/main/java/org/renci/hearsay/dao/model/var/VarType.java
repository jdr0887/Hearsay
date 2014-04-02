package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "var_type")
public class VarType {

    @Lob
    @Column(name = "var_type_name")
    private String typeName;

}
