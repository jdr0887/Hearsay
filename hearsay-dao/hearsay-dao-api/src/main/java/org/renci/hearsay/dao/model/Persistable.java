package org.renci.hearsay.dao.model;

import java.io.Serializable;

public interface Persistable extends Serializable {

    /**
     * 
     * @return
     */
    public Long getPrimaryKey();

}
