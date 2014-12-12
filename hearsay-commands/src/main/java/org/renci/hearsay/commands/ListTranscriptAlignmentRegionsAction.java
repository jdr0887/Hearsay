package org.renci.hearsay.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.AbstractAction;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptAlignmentDAO;
import org.renci.hearsay.dao.model.Region;
import org.renci.hearsay.dao.model.TranscriptAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(scope = "hearsay", name = "list-transcript-alignment-regions", description = "List Transcript Alignment Regions")
public class ListTranscriptAlignmentRegionsAction extends AbstractAction {

    private final Logger logger = LoggerFactory.getLogger(ListTranscriptAlignmentRegionsAction.class);

    private HearsayDAOBean hearsayDAOBean;

    @Argument(index = 0, name = "transcriptAlignmentId", description = "TranscriptAlignment Identifier", required = true, multiValued = false)
    private Long transcriptAlignmentId;

    public ListTranscriptAlignmentRegionsAction() {
        super();
    }

    @Override
    public Object doExecute() {
        logger.debug("ENTERING doExecute()");
        TranscriptAlignmentDAO transcriptAlignmentDAO = hearsayDAOBean.getTranscriptAlignmentDAO();

        TranscriptAlignment alignment = null;
        try {
            alignment = transcriptAlignmentDAO.findById(transcriptAlignmentId);
        } catch (HearsayDAOException e) {
        }
        if (alignment == null) {
            System.out.println("No TranscriptAlignment found");
            return null;
        }
        String format = "%1$-10s %2$-10s %3$-20s %4$-20s %5$-20s %6$-20s %7$-20s %8$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "Type", "Start", "Stop", "Transcript Start", "Transcript Stop", "CDS Start",
                "CDS Stop");

        List<Region> regions = new ArrayList<>(alignment.getRegions());

        Collections.sort(regions, new Comparator<org.renci.hearsay.dao.model.Region>() {
            @Override
            public int compare(Region o1, Region o2) {
                return o1.getRegionStart().compareTo(o2.getRegionStart());
            }
        });

        for (Region region : regions) {
            formatter.format(format, region.getId(), region.getRegionType().getValue(), region.getRegionStart(), region
                    .getRegionStop(), region.getTranscriptStart() != null ? region.getTranscriptStart() : "", region
                    .getTranscriptStop() != null ? region.getTranscriptStop() : "",
                    region.getCdsStart() != null ? region.getCdsStart().toString() : "",
                    region.getCdsStop() != null ? region.getCdsStop().toString() : "");
            formatter.flush();
        }
        System.out.println(formatter.toString());
        formatter.close();

        return null;
    }

    public Long getTranscriptAlignmentId() {
        return transcriptAlignmentId;
    }

    public void setTranscriptAlignmentId(Long transcriptAlignmentId) {
        this.transcriptAlignmentId = transcriptAlignmentId;
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
