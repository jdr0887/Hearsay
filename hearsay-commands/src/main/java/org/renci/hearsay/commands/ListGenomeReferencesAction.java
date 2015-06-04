package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-genome-references", description = "List Genome References")
public class ListGenomeReferencesAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(ListGenomeReferencesAction.class);

    private HearsayDAOBean hearsayDAOBean;

    public ListGenomeReferencesAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");

        String format = "%1$-10s %2$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Name");

        List<GenomeReference> genomeReferenceList = new ArrayList<GenomeReference>();
        try {
            genomeReferenceList.addAll(hearsayDAOBean.getGenomeReferenceDAO().findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        for (GenomeReference genomeReference : genomeReferenceList) {
            formatter.format(format, genomeReference.getId().toString(), genomeReference.getName());
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
