package org.renci.hearsay.dao.model.opm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@javax.persistence.Entity
@Table(name = "opm_value")
public class Value extends Element {

    private static final long serialVersionUID = 8138582830128718779L;

    @XmlTransient
    @Column(name = "int")
    protected Integer integer;

    @XmlTransient
    @Column(name = "string")
    protected String string;

    @XmlTransient
    @Column(name = "long_", precision = 20, scale = 0)
    protected Long _long;

    @XmlTransient
    @Column(name = "short_")
    protected Short _short;

    @XmlTransient
    @Column(name = "double_", precision = 20, scale = 10)
    protected Double _double;

    @XmlTransient
    @Column(name = "float_", precision = 20, scale = 10)
    protected Float _float;

    @XmlTransient
    @Column(name = "decimal_", precision = 20, scale = 10)
    protected BigDecimal decimal;

    @XmlTransient
    @Column(name = "boolean_")
    protected Boolean _boolean;

    @XmlTransient
    @Column(name = "byte_")
    protected Byte _byte;

    @XmlTransient
    @Column(name = "any_uri")
    protected String anyURI;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "qualified_name_fk")
    protected QualifiedName qualifiedName;

    @XmlTransient
    @Column(name = "unsigned_int")
    protected Long unsignedInt;

    @XmlTransient
    @Column(name = "unsigned_long")
    protected BigInteger unsignedLong;

    @XmlTransient
    @Column(name = "date_time")
    protected Date dateTime;

    public Value() {
        super();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Long get_long() {
        return _long;
    }

    public void set_long(Long _long) {
        this._long = _long;
    }

    public Short get_short() {
        return _short;
    }

    public void set_short(Short _short) {
        this._short = _short;
    }

    public Double get_double() {
        return _double;
    }

    public void set_double(Double _double) {
        this._double = _double;
    }

    public Float get_float() {
        return _float;
    }

    public void set_float(Float _float) {
        this._float = _float;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public Boolean get_boolean() {
        return _boolean;
    }

    public void set_boolean(Boolean _boolean) {
        this._boolean = _boolean;
    }

    public Byte get_byte() {
        return _byte;
    }

    public void set_byte(Byte _byte) {
        this._byte = _byte;
    }

    public String getAnyURI() {
        return anyURI;
    }

    public void setAnyURI(String anyURI) {
        this.anyURI = anyURI;
    }

    public QualifiedName getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(QualifiedName qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public Long getUnsignedInt() {
        return unsignedInt;
    }

    public void setUnsignedInt(Long unsignedInt) {
        this.unsignedInt = unsignedInt;
    }

    public BigInteger getUnsignedLong() {
        return unsignedLong;
    }

    public void setUnsignedLong(BigInteger unsignedLong) {
        this.unsignedLong = unsignedLong;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String
                .format("Value [integer=%s, string=%s, _long=%s, _short=%s, _double=%s, _float=%s, decimal=%s, _boolean=%s, _byte=%s, anyURI=%s, qualifiedName=%s, unsignedInt=%s, unsignedLong=%s, dateTime=%s]",
                        integer, string, _long, _short, _double, _float, decimal, _boolean, _byte, anyURI,
                        qualifiedName, unsignedInt, unsignedLong, dateTime);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_boolean == null) ? 0 : _boolean.hashCode());
        result = prime * result + ((_byte == null) ? 0 : _byte.hashCode());
        result = prime * result + ((_double == null) ? 0 : _double.hashCode());
        result = prime * result + ((_float == null) ? 0 : _float.hashCode());
        result = prime * result + ((_long == null) ? 0 : _long.hashCode());
        result = prime * result + ((_short == null) ? 0 : _short.hashCode());
        result = prime * result + ((anyURI == null) ? 0 : anyURI.hashCode());
        result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
        result = prime * result + ((decimal == null) ? 0 : decimal.hashCode());
        result = prime * result + ((integer == null) ? 0 : integer.hashCode());
        result = prime * result + ((qualifiedName == null) ? 0 : qualifiedName.hashCode());
        result = prime * result + ((string == null) ? 0 : string.hashCode());
        result = prime * result + ((unsignedInt == null) ? 0 : unsignedInt.hashCode());
        result = prime * result + ((unsignedLong == null) ? 0 : unsignedLong.hashCode());
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
        Value other = (Value) obj;
        if (_boolean == null) {
            if (other._boolean != null)
                return false;
        } else if (!_boolean.equals(other._boolean))
            return false;
        if (_byte == null) {
            if (other._byte != null)
                return false;
        } else if (!_byte.equals(other._byte))
            return false;
        if (_double == null) {
            if (other._double != null)
                return false;
        } else if (!_double.equals(other._double))
            return false;
        if (_float == null) {
            if (other._float != null)
                return false;
        } else if (!_float.equals(other._float))
            return false;
        if (_long == null) {
            if (other._long != null)
                return false;
        } else if (!_long.equals(other._long))
            return false;
        if (_short == null) {
            if (other._short != null)
                return false;
        } else if (!_short.equals(other._short))
            return false;
        if (anyURI == null) {
            if (other.anyURI != null)
                return false;
        } else if (!anyURI.equals(other.anyURI))
            return false;
        if (dateTime == null) {
            if (other.dateTime != null)
                return false;
        } else if (!dateTime.equals(other.dateTime))
            return false;
        if (decimal == null) {
            if (other.decimal != null)
                return false;
        } else if (!decimal.equals(other.decimal))
            return false;
        if (integer == null) {
            if (other.integer != null)
                return false;
        } else if (!integer.equals(other.integer))
            return false;
        if (qualifiedName == null) {
            if (other.qualifiedName != null)
                return false;
        } else if (!qualifiedName.equals(other.qualifiedName))
            return false;
        if (string == null) {
            if (other.string != null)
                return false;
        } else if (!string.equals(other.string))
            return false;
        if (unsignedInt == null) {
            if (other.unsignedInt != null)
                return false;
        } else if (!unsignedInt.equals(other.unsignedInt))
            return false;
        if (unsignedLong == null) {
            if (other.unsignedLong != null)
                return false;
        } else if (!unsignedLong.equals(other.unsignedLong))
            return false;
        return true;
    }

}
