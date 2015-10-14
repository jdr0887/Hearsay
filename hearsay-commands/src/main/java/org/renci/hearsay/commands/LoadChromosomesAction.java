package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.renci.hearsay.dao.ChromosomeDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Chromosome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "load-chromosomes", description = "Load Chromosomes")
@Service
public class LoadChromosomesAction implements Action {

    private final Logger logger = LoggerFactory.getLogger(LoadChromosomesAction.class);

    @Reference
    private ChromosomeDAO chromosomeDAO;

    public LoadChromosomesAction() {
        super();
    }

    @Override
    public Object execute() {
        logger.debug("ENTERING doExecute()");

        List<Chromosome> allChromosomes = new ArrayList<Chromosome>();

        try {
            allChromosomes.addAll(chromosomeDAO.findAll());
            if (CollectionUtils.isNotEmpty(allChromosomes)) {
                chromosomeDAO.delete(allChromosomes);
            }
        } catch (HearsayDAOException e1) {
            e1.printStackTrace();
        }

        try {
            for (int i = 1; i < 23; i++) {
                chromosomeDAO.save(new Chromosome(i + ""));
            }
            chromosomeDAO.save(new Chromosome("X"));
            chromosomeDAO.save(new Chromosome("Y"));
            chromosomeDAO.save(new Chromosome("MT"));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
