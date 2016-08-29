package de.micha.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.micha.domain.Patient;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by micha on 28.08.16.
 */

@Service
public class PatientService {

    private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

    @Inject
    private Client elasticClient;

    @Inject
    @Named("index")
    private String index;

    @Inject
    @Named("type")
    private String type;

    public Optional<Patient> getPatient(long id) {

        GetResponse response = elasticClient.prepareGet(index, type, String.valueOf(id)).get();

        String json = response.getSourceAsString();
        if (response.isSourceEmpty()){
            //TODO return 404
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //JSON from String to Object
        try {
            Patient user = mapper.readValue(json, Patient.class);
            return Optional.of(user);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return Optional.empty();
        }



    }

}
