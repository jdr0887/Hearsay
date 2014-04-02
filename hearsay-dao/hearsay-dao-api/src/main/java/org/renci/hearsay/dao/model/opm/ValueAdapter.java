package org.renci.hearsay.dao.model.opm;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ValueAdapter extends XmlAdapter<String, Object> {

    @Override
    public String marshal(Object o) throws Exception {
        if (o == null) {
            return null;
        } else {
            return o.toString();
        }
    }

    @Override
    public Object unmarshal(String ref) throws Exception {
        if (ref == null) {
            return null;
        } else {
            return ref;
        }
    }

}
