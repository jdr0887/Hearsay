package org.renci.hearsay.dao.model.opm;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

public class IDRefAdapter extends XmlAdapter<IDRef, QualifiedName> {

    @Override
    public IDRef marshal(QualifiedName qname) throws Exception {
        if (qname == null) {
            return null;
        } else {
            IDRef res = new IDRef();
            res.setRef(qname.toQName());
            return res;
        }
    }

    @Override
    public QualifiedName unmarshal(IDRef ref) throws Exception {
        if (ref == null) {
            return null;
        } else {
            QName q = ref.getRef();
            QualifiedName qq = new QualifiedName(q.getNamespaceURI(), q.getLocalPart(), q.getPrefix());
            return qq;
        }
    }

}
