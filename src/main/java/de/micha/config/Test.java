package de.micha.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.micha.domain.Patient;

import java.io.IOException;

/**
 * Created by micha on 29.08.16.
 */
public class Test {


    public static void main(String[] args) {
        String json = "{\"first_name\" : \"Ana\", \"last_name\" :  \"Herz\", " +
                "\"age\" : 40, \"interests\": [ \"music\" ]}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //JSON from String to Object

        try {
            Patient patient = mapper.readValue(json, Patient.class);
            System.out.println(patient.getFirstName() + " " + patient.getLastName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
