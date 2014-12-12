package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.commands.Option;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptRefSeqDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.TranscriptRefSeq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-transcript-refseqs", description = "List TranscriptRefSeqs")
public class ListTranscriptRefSeqsAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(ListTranscriptRefSeqsAction.class);

    private HearsayDAOBean hearsayDAOBean;

    @Option(name = "--accession", description = "Accession", required = false, multiValued = false)
    private String accession;

    @Option(name = "--geneName", description = "Gene Name", required = false, multiValued = false)
    private String geneName;

    public ListTranscriptRefSeqsAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");
        TranscriptRefSeqDAO transcriptRefSeqDAO = hearsayDAOBean.getTranscriptRefSeqDAO();

        List<TranscriptRefSeq> transcripts = new ArrayList<>();
        try {

            if (StringUtils.isNotEmpty(accession)) {
                List<TranscriptRefSeq> foundTranscripts = transcriptRefSeqDAO.findByAccession(accession);
                if (foundTranscripts != null && !foundTranscripts.isEmpty()) {
                    transcripts.addAll(foundTranscripts);
                }
            }

            if (StringUtils.isNotEmpty(geneName)) {
                List<TranscriptRefSeq> foundTranscripts = transcriptRefSeqDAO.findByGeneName(geneName);
                if (foundTranscripts != null && !foundTranscripts.isEmpty()) {
                    transcripts.addAll(foundTranscripts);
                }
            }

        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        
        if (transcripts.isEmpty()) {
            System.out.println("No Transcripts found");
            return null;
        }
        
        String format = "%1$-10s %2$-24s %3$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Accession", "GeneName");
        for (TranscriptRefSeq transcript : transcripts) {
            Gene gene = transcript.getGene();
            formatter.format(format, transcript.getId(), transcript.getAccession(), gene.getName());
            formatter.flush();
        }
        System.out.println(formatter.toString());
        formatter.close();

        return null;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
