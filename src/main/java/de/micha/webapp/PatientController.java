package de.micha.webapp;

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
    public List<PatientView> getUser(@PathVariable(value = "id") Long id) {
        LOG.info("customerId:{}", id);
        return patientApp.getPatinent(id);
    }


}
