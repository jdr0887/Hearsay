package org.renci.hearsay.dao.model.opm;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

public class QNameAdapter extends XmlAdapter<QName, QualifiedName> {

    @Override
    public QName marshal(QualifiedName qname) throws Exception {
        if (qname == null) {
            return null;
        } else {
            return qname.toQName();
        }
    }

    @Override
    public QualifiedName unmarshal(QName q) throws Exception {
        if (q == null) {
            return null;
        } else {
            return new QualifiedName(q.getNamespaceURI(), q.getLocalPart(), q.getPrefix());
        }
    }

}
