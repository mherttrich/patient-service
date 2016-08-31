package de.micha.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by micha on 29.08.16.
 */
public class Patient {

    @JsonProperty
    private String first_name;
    @JsonProperty
    private String last_name;
    @JsonProperty
    private String gender;
    @JsonProperty
    private String date_of_birth;
    @JsonProperty
    private int weight; //kg
    @JsonProperty
    private int height; //cm
    @JsonProperty
    private String city;
    @JsonProperty
    private String street;
    @JsonProperty
    private String postcode;
    @JsonProperty
    private String health_insurance_number;
    private List<String> medical_history;

    public List<String> getMedical_history() {
        return medical_history;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHealthInsuranceNumber() {
        return health_insurance_number;
    }

    public void setFirstName(String firsName) {
        this.first_name = firsName;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setHealthInsuranceNumber(String healthhealthInsuranceNumber) {
        this.health_insurance_number = healthhealthInsuranceNumber;
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medical_history = medicalHistory;
    }
}
