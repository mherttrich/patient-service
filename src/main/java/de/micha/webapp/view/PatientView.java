package de.micha.webapp.view;


import java.util.List;

public class PatientView {
    private String name;
    private String gender;
    private int height;
    private int weight;

    private String address;
    private String dateOfBirth;
    private List<String> medicalHistory;

    private String healthInsuranceNumber;

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }
}
