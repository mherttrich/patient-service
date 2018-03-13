package de.micha.service;

import de.micha.domain.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

    public Optional<Patient> getPatient(long id) {
        return elasticDao.get(index, type, String.valueOf(id), Patient.class);
    }

    public void postPatient(Patient patient) {
        elasticDao.save(index, type, patient);
    }

}
