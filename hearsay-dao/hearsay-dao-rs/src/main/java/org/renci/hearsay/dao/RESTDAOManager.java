package org.renci.hearsay.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author jdr0887
 */
public class RESTDAOManager {

    private static RESTDAOManager instance;

    private HearsayDAOBeanService hearsayDAOBeanService;

    public static RESTDAOManager getInstance() {
        if (instance == null) {
            instance = new RESTDAOManager();
        }
        return instance;
    }

    private RESTDAOManager() {
        super();
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/org/renci/hearsay/dao/rs/hearsay-dao-beans.xml")) {
            this.hearsayDAOBeanService = applicationContext.getBean(HearsayDAOBeanService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HearsayDAOBeanService getHearsayDAOBeanService() {
        return hearsayDAOBeanService;
    }

}
