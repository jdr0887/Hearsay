package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-genes", description = "List Genes")
public class ListGenesAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(ListGenesAction.class);

    private HearsayDAOBean hearsayDAOBean;

    public ListGenesAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");

        String format = "%1$-10s %2$-24s %3$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Name", "Symbol", "Description");

        List<Gene> geneList = new ArrayList<>();
        try {
            geneList.addAll(hearsayDAOBean.getGeneDAO().findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        for (Gene gene : geneList) {
            formatter.format(format, gene.getId(), gene.getName(), gene.getSymbol(), gene.getDescription());
            formatter.flush();
        }
        System.out.println(formatter.toString());
        formatter.close();

        return null;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
