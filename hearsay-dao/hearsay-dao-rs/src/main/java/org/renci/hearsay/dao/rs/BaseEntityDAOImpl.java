package org.renci.hearsay.dao.rs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.BaseEntityDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public abstract class BaseEntityDAOImpl<T extends Persistable, ID extends Serializable> implements BaseEntityDAO<T, ID> {

    private Class<T> persistentClass;

    private final List<Object> providers = new ArrayList<Object>();

    private String host;

    private String restServiceURL;

    public BaseEntityDAOImpl() {
        super();
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        providers.add(provider);
    }

    public BaseEntityDAOImpl(Class<T> persistentClass) {
        this();
        this.persistentClass = persistentClass;
    }

    @Override
    public void delete(List<T> idList) throws HearsayDAOException {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(T entity) throws HearsayDAOException {
        // TODO Auto-generated method stub

    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public List<Object> getProviders() {
        return providers;
    }

    public String getRestServiceURL() {
        return restServiceURL;
    }

    public void setRestServiceURL(String restServiceURL) {
        this.restServiceURL = restServiceURL;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
