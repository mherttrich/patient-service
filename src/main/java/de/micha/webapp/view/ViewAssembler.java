package de.micha.webapp.view;


import de.micha.domain.Patient;

public class ViewAssembler {

    private static final short ADULT_AGE = 18;

    public static PatientView assemble(Patient patient) {
        PatientView view = new PatientView();
        view.setName(String.format("%s %s", patient.getLastName(), patient.getFirstName()));
        view.setAddress(String.format("%s, %s %s", patient.getStreet(), patient.getPostcode(), patient.getCity()));
        view.setGender(patient.getGender());
        view.setDateOfBirth(patient.getDateOfBirth());
        view.setHeight(patient.getHeight());
        view.setWeight(patient.getWeight());
        view.setHealthInsuranceNumber(patient.getHealthInsuranceNumber());
        view.setMedicalHistory(patient.getMedical_history());


        return view;
    }
}
