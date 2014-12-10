package org.renci.hearsay.dao.rs;

import org.renci.hearsay.dao.HearsayDAOBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author jdr0887
 */
public class RESTDAOManager {

    private static RESTDAOManager instance;

    private String beanXMLFile = "org/renci/hearsay/dao/rs/hearsay-dao-beans.xml";

    private ClassPathXmlApplicationContext applicationContext = null;

    public static RESTDAOManager getInstance() {
        if (instance == null) {
            instance = new RESTDAOManager();
        }
        return instance;
    }

    public static RESTDAOManager getInstance(String beanXMLFile) {
        if (instance == null) {
            instance = new RESTDAOManager(beanXMLFile);
        }
        return instance;
    }

    private RESTDAOManager() {
        this.applicationContext = new ClassPathXmlApplicationContext(this.beanXMLFile);
    }

    private RESTDAOManager(String beanXMLFile) {
        this.beanXMLFile = beanXMLFile;
        this.applicationContext = new ClassPathXmlApplicationContext(this.beanXMLFile);
    }

    public HearsayDAOBean getHearsayDAOBean() {
        HearsayDAOBean bean = (HearsayDAOBean) applicationContext.getBean("hearsayDAOBean", HearsayDAOBean.class);
        return bean;
    }

}
