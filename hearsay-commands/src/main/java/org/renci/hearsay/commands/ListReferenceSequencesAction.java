package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-reference-sequences", description = "List Reference Sequences")
public class ListReferenceSequencesAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(ListReferenceSequencesAction.class);

    private HearsayDAOBean hearsayDAOBean;

    public ListReferenceSequencesAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");

        String format = "%1$-10s %2$-20s %3$-20s %4$-20s %5$-12s %6$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Accession", "Type");

        List<ReferenceSequence> referenceSequenceList = new ArrayList<ReferenceSequence>();
        try {
            referenceSequenceList.addAll(hearsayDAOBean.getReferenceSequenceDAO().findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        for (ReferenceSequence refSeq : referenceSequenceList) {

            String rnaAccession = "";
            if (refSeq.getIdentifiers() != null && !refSeq.getIdentifiers().isEmpty()) {
                for (Identifier identifier : refSeq.getIdentifiers()) {
                    if (identifier.getSystem().endsWith("nuccore")) {
                        rnaAccession = identifier.getValue();
                    }
                }
            }

            String proteinAccession = "";
            if (refSeq.getIdentifiers() != null && !refSeq.getIdentifiers().isEmpty()) {
                for (Identifier identifier : refSeq.getIdentifiers()) {
                    if (identifier.getSystem().endsWith("protein")) {
                        proteinAccession = identifier.getValue();
                    }
                }
            }

            String genomicAccession = "";
            if (refSeq.getIdentifiers() != null && !refSeq.getIdentifiers().isEmpty()) {
                for (Identifier identifier : refSeq.getIdentifiers()) {
                    if (identifier.getSystem().endsWith("genome")) {
                        genomicAccession = identifier.getValue();
                    }
                }
            }

            formatter.format(format, refSeq.getId().toString(), rnaAccession, proteinAccession, genomicAccession,
                    refSeq.getStrandType().toString(), refSeq.getType().toString());
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
