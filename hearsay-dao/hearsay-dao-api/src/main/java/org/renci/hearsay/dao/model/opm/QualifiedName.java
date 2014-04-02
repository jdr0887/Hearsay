package org.renci.hearsay.dao.model.opm;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QualifiedName")
@NamedQuery(name = "QualifiedName.Find", query = "SELECT a FROM QualifiedName a WHERE a.namespaceURI LIKE :namespaceURI")
@javax.persistence.Entity
@Table(name = "opm_qualified_name")
public class QualifiedName extends Element {

    private static final long serialVersionUID = 8817857567472280656L;

    @Column(name = "namespace_uri")
    protected String namespaceURI;

    @Column(name = "local_part")
    protected String localPart;

    @Column(name = "prefix")
    protected String prefix;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "membership_fk")
    private Membership membership;

    public QualifiedName() {
        super();
    }

    public QualifiedName(String namespaceURI, String localPart, String prefix) {
        this.namespaceURI = namespaceURI;
        this.localPart = localPart;
        this.prefix = prefix;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    public String getLocalPart() {
        return localPart;
    }

    public void setLocalPart(String localPart) {
        this.localPart = localPart;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public QName toQName() {
        if (prefix == null) {
            return new QName(namespaceURI, localPart);
        } else {
            return new QName(namespaceURI, localPart, prefix);
        }
    }

    @Override
    public String toString() {
        return String.format("QualifiedName [primaryKey=%s, namespaceURI=%s, localPart=%s, prefix=%s, membership=%s]",
                primaryKey, namespaceURI, localPart, prefix, membership);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localPart == null) ? 0 : localPart.hashCode());
        result = prime * result + ((membership == null) ? 0 : membership.hashCode());
        result = prime * result + ((namespaceURI == null) ? 0 : namespaceURI.hashCode());
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
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
        QualifiedName other = (QualifiedName) obj;
        if (localPart == null) {
            if (other.localPart != null)
                return false;
        } else if (!localPart.equals(other.localPart))
            return false;
        if (membership == null) {
            if (other.membership != null)
                return false;
        } else if (!membership.equals(other.membership))
            return false;
        if (namespaceURI == null) {
            if (other.namespaceURI != null)
                return false;
        } else if (!namespaceURI.equals(other.namespaceURI))
            return false;
        if (prefix == null) {
            if (other.prefix != null)
                return false;
        } else if (!prefix.equals(other.prefix))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        return true;
    }

}
