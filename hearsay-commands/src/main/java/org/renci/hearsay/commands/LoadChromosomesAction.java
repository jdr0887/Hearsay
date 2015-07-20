package org.renci.hearsay.commands;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Chromosome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "load-chromosomes", description = "Load Chromosomes")
public class LoadChromosomesAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(LoadChromosomesAction.class);

    private HearsayDAOBean hearsayDAOBean;

    public LoadChromosomesAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");
        try {
            for (int i = 1; i < 23; i++) {
                hearsayDAOBean.getChromosomeDAO().save(new Chromosome(i + ""));
            }
            hearsayDAOBean.getChromosomeDAO().save(new Chromosome("X"));
            hearsayDAOBean.getChromosomeDAO().save(new Chromosome("Y"));
            hearsayDAOBean.getChromosomeDAO().save(new Chromosome("MT"));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
