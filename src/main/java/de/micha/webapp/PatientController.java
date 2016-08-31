package de.micha.webapp;

import de.micha.domain.Patient;
import de.micha.webapp.view.PatientView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping(value = "resource/")
class PatientController {

    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);

    @Inject
    private PatientApp patientApp;

    @RequestMapping(value = "patient/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<PatientView> getPatient(@PathVariable(value = "id") Long id) {
        return patientApp.getPatinent(id);
    }

    @RequestMapping(value = "patient/", method = RequestMethod.POST)
    @ResponseBody
    public void postPatient(@RequestBody PatientView patient) {
        //try nested objects
        patientApp.postPatient(patient);
    }


}
