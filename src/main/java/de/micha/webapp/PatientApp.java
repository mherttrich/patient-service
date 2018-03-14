package de.micha.webapp;


import com.google.common.collect.Lists;
import de.micha.domain.Patient;
import de.micha.service.PatientService;
import de.micha.webapp.view.PatientView;
import de.micha.webapp.view.ViewAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Component
class PatientApp {

    @Inject
    private  PatientService elasticSearchService;


    List<PatientView> getPatinent(String customerId) {
        List<PatientView> views = Lists.newArrayList();

        Optional<Patient> patient = elasticSearchService.getPatient(customerId);
        if (patient.isPresent()) {
            //views might remain empty, if Optional is empty
            patient.map((p -> views.add(ViewAssembler.assemble(p))));
        } else{//TODO
            // return 404

        }
        return views;
    }


    void postPatient(PatientView view){
        Patient p = new Patient();
        p.setLastName(view.getName());
        p.setHeight(view.getHeight());
        elasticSearchService.postPatient(p);
    }
}
