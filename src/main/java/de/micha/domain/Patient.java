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
}
