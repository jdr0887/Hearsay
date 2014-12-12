package org.renci.hearsay.dao.rs;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.renci.hearsay.dao.BaseEntityDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public abstract class BaseEntityDAOImpl<T extends Persistable, ID extends Serializable> implements BaseEntityDAO<T, ID> {

    private Class<T> persistentClass;

    private final List<Object> providers = new ArrayList<Object>();

    private String restServiceURL;

    private PropertiesConfiguration config;

    public BaseEntityDAOImpl() {
        super();
        String userHome = System.getProperty("user.home");
        File hiddenHearsayDir = new File(userHome, ".hearsay");
        if (!hiddenHearsayDir.exists()) {
            hiddenHearsayDir.mkdirs();
        }
        File hearsayConfigFile = new File(hiddenHearsayDir, "config.properties");
        try {
            config = new PropertiesConfiguration(hearsayConfigFile);
            if (!hearsayConfigFile.exists()) {
                try {
                    FileUtils.touch(hearsayConfigFile);
                    config.setProperty("host", "localhost");
                    config.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        providers.add(provider);
    }

    public BaseEntityDAOImpl(Class<T> persistentClass) {
        this();
        this.persistentClass = persistentClass;
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", config.getString("host"), 8181,
                this.persistentClass.getSimpleName()));
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

}
