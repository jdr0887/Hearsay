package org.renci.hearsay.web;

import org.renci.hearsay.dao.model.Participant;

public class ParticipantForm {

    private Participant participant;

    public ParticipantForm() {
        super();
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

}
