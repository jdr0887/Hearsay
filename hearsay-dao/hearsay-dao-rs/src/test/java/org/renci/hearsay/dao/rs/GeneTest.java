package org.renci.hearsay.dao.rs;

import java.util.List;

import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.RESTDAOManager;
import org.renci.hearsay.dao.model.Gene;

public class GeneTest {

    @Test
    public void testFindBySymbol() {
        RESTDAOManager daoMgr = RESTDAOManager.getInstance();
        try {
            List<Gene> geneList = daoMgr.getHearsayDAOBeanService().getGeneDAO().findBySymbol("BRCA1");
            geneList.forEach(a -> System.out.println(a.toString()));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
    }
}
