package org.renci.hearsay.web;

import java.io.IOException;

import org.junit.Test;
import org.renci.hearsay.dao.model.Participant;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestJSONSerialization {

    @Test
    public void scratch() {
        Participant p = new Participant("asdf");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        try {
            System.out.println(mapper.writeValueAsString(p));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
