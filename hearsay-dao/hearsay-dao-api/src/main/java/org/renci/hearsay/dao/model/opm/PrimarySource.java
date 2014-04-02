package org.renci.hearsay.dao.model.opm;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PrimarySource complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrimarySource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/ns/prov#}Derivation">
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrimarySource")
@javax.persistence.Entity
@Table(name = "opm_primary_source")
public class PrimarySource extends Derivation {

    private static final long serialVersionUID = -5022276831254059251L;

}
