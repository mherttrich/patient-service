package de.micha.service;

import com.google.common.collect.ImmutableMap;
import de.micha.domain.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by micha on 28.08.16.
 */

@Service
public class PatientService {

    private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

    @Inject
    private ElasticDao elasticDao;

    @Value("${elastic.index}")
    private String index;

    @Value("${elastic.type}")
    private String type;

    public Optional<Patient> getPatient(String id) {
        return elasticDao.get(index, type, id, Patient.class);
    }

    public void postPatient(Patient patient) {
        elasticDao.save(index, type, patient);
    }

    public List<Patient> searchPatient(String lastName, String insuranceNumber){
    ImmutableMap<String, String> params = ImmutableMap.of(
            "last_name", lastName,
            "health_insurance_number", insuranceNumber);

        try {
            return elasticDao.search(index,type , params,  Patient.class);
        } catch (IOException e) {
            LOG.error("could not find patient {} {}", lastName, insuranceNumber);
            //todo general exeption mapping, return 404
            throw new RuntimeException("");
        }
    }

}
