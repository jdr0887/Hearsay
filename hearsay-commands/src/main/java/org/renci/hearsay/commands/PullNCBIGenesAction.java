package org.renci.hearsay.commands;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "pull-ncbi-genes", description = "Pull NCBI Genes")
public class PullNCBIGenesAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(PullNCBIGenesAction.class);

    private HearsayDAOBean hearsayDAOBean;

    public PullNCBIGenesAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");
        PullNCBIGenesRunnable runnable = new PullNCBIGenesRunnable();
        runnable.setHearsayDAOBean(hearsayDAOBean);
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(runnable);
        es.shutdown();
        return null;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
