package edu.unc.mapseq.dao.rs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.provider.json.JSONProvider;

import edu.unc.mapseq.dao.BaseDAO;
import edu.unc.mapseq.dao.MaPSeqDAOException;
import edu.unc.mapseq.dao.model.Persistable;

public abstract class BaseDAOImpl<T extends Persistable, ID extends Serializable> implements BaseDAO<T, ID> {

    private Class<T> persistentClass;

    private final List<Object> providers = new ArrayList<Object>();

    private String restServiceURL;

    public BaseDAOImpl() {
        super();
        JSONProvider<T> provider = new JSONProvider<T>();
        provider.setDropRootElement(true);
        providers.add(provider);
    }

    public BaseDAOImpl(Class<T> persistentClass) {
        this();
        this.persistentClass = persistentClass;
    }

    @Override
    public void delete(List<T> idList) throws MaPSeqDAOException {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(T entity) throws MaPSeqDAOException {
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

}
