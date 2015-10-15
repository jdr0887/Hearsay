package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

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

@Command(scope = "hearsay", name = "list-chromosomes", description = "List Chromosomes")
@Service
public class ListChromosomesAction implements Action {

    private final Logger logger = LoggerFactory.getLogger(ListChromosomesAction.class);

    @Reference
    private ChromosomeDAO chromosomeDAO;

    public ListChromosomesAction() {
        super();
    }

    @Override
    public Object execute() {
        logger.debug("ENTERING execute()");

        String format = "%1$-10s %2$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Name");

        List<Chromosome> chromosomeList = new ArrayList<Chromosome>();
        try {
            chromosomeList.addAll(chromosomeDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        if (CollectionUtils.isNotEmpty(chromosomeList)) {

            for (Chromosome chromosome : chromosomeList) {
                formatter.format(format, chromosome.getId().toString(), chromosome.getName());
                formatter.flush();
            }

        }
        System.out.println(formatter.toString());
        formatter.close();

        return null;
    }

}
