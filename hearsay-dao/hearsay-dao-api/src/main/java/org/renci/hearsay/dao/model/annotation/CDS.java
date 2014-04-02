package org.renci.hearsay.dao.model.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDS")
@XmlRootElement(name = "cds")
@Entity
@Table(name = "cds")
public class CDS {

    @Column(name = "cds_id")
    private Long cdsId;

    @Column(name = "preferred_name")
    private String preferredName;

    public CDS() {
        super();
    }

    public Long getCdsId() {
        return cdsId;
    }

    public void setCdsId(Long cdsId) {
        this.cdsId = cdsId;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    @Override
    public String toString() {
        return String.format("CDS [cdsId=%s, preferredName=%s]", cdsId, preferredName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdsId == null) ? 0 : cdsId.hashCode());
        result = prime * result + ((preferredName == null) ? 0 : preferredName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CDS other = (CDS) obj;
        if (cdsId == null) {
            if (other.cdsId != null)
                return false;
        } else if (!cdsId.equals(other.cdsId))
            return false;
        if (preferredName == null) {
            if (other.preferredName != null)
                return false;
        } else if (!preferredName.equals(other.preferredName))
            return false;
        return true;
    }

}
