package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-genes", description = "List Genes")
@Service
public class ListGenesAction implements Action {

    private final Logger logger = LoggerFactory.getLogger(ListGenesAction.class);

    @Reference
    private GeneDAO geneDAO;

    public ListGenesAction() {
        super();
    }

    @Override
    public Object execute() {
        logger.debug("ENTERING execute()");

        String format = "%1$-10s %2$-16s %3$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Symbol", "Description");

        List<Gene> geneList = new ArrayList<Gene>();
        try {
            geneList.addAll(geneDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        for (Gene gene : geneList) {
            formatter.format(format, gene.getId().toString(), gene.getSymbol(), gene.getDescription());
            formatter.flush();
        }
        System.out.println(formatter.toString());
        formatter.close();

        return null;
    }

}
