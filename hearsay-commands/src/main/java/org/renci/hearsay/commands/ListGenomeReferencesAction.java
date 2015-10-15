package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-genome-references", description = "List Genome References")
@Service
public class ListGenomeReferencesAction implements Action {

    private final Logger logger = LoggerFactory.getLogger(ListGenomeReferencesAction.class);

    @Reference
    private GenomeReferenceDAO genomeReferenceDAO;

    public ListGenomeReferencesAction() {
        super();
    }

    @Override
    public Object execute() {
        logger.debug("ENTERING execute()");

        String format = "%1$-10s %2$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Name");

        List<GenomeReference> genomeReferenceList = new ArrayList<GenomeReference>();
        try {
            genomeReferenceList.addAll(genomeReferenceDAO.findAll());
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

}
