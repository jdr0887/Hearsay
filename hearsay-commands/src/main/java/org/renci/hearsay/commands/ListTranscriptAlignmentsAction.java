package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptAlignmentDAO;
import org.renci.hearsay.dao.model.TranscriptAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-transcript-alignments", description = "List Transcript Alignments")
public class ListTranscriptAlignmentsAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(ListTranscriptAlignmentsAction.class);

    private HearsayDAOBean hearsayDAOBean;

    @Argument(index = 0, name = "transcriptRefSeqId", description = "TranscriptRefSeq Identifier", required = true, multiValued = false)
    private Long transcriptRefSeqId;

    public ListTranscriptAlignmentsAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");
        TranscriptAlignmentDAO transcriptAlignmentDAO = hearsayDAOBean.getTranscriptAlignmentDAO();

        List<TranscriptAlignment> alignments = new ArrayList<>();
        try {
            List<TranscriptAlignment> foundAlignments = transcriptAlignmentDAO.findByTranscriptRefSeqId(transcriptRefSeqId);
            if (foundAlignments != null && !foundAlignments.isEmpty()) {
                alignments.addAll(foundAlignments);
            }
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        if (alignments.isEmpty()) {
            System.out.println("No TranscriptAlignments found");
            return null;
        }
        String format = "%1$-10s %2$-10s %3$-20s %4$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Strand", "Genomic Start", "Genomic Stop");
        for (TranscriptAlignment alignment : alignments) {
            formatter.format(format, alignment.getId(), alignment.getStrandType().getValue(), alignment.getGenomicStart(), alignment.getGenomicStop());
            formatter.flush();
        }
        System.out.println(formatter.toString());
        formatter.close();

        return null;
    }

    public Long getTranscriptRefSeqId() {
        return transcriptRefSeqId;
    }

    public void setTranscriptRefSeqId(Long transcriptRefSeqId) {
        this.transcriptRefSeqId = transcriptRefSeqId;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
