package de.micha.webapp;

import de.micha.domain.Patient;
import de.micha.service.PatientService;
import de.micha.webapp.view.PatientView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping(value = "resource/")
class PatientController {

    private static final Logger LOG = LoggerFactory.getLogger(PatientController.class);

    @Inject
    private PatientApp patientApp;
    @Inject
    private PatientService patientService;

    @RequestMapping(value = "patient/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<PatientView> getPatient(@PathVariable(value = "id") String id) {
        return patientApp.getPatinent(id);
    }

    @RequestMapping(value = "patient", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> postPatient(@RequestBody PatientView patient) {
        //try nested objects
        patientApp.postPatient(patient);
        return ResponseEntity.accepted().build();
    }


    @RequestMapping(value = "patient", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Patient>> getPatient(@RequestParam("lastName") String lastName, @RequestParam("insuranceNumber") String insuranceNumber)  {
        return ResponseEntity.ok(patientService.searchPatient(lastName, insuranceNumber));
    }

}
