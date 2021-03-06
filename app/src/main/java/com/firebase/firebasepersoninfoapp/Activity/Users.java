package com.firebase.firebasepersoninfoapp.Activity;

public class Users {
    private String uid;
    private String firstname;

    public String getUid() {
        return uid;
    }

    private String lastname;
    private String age;
    private String email;
    private String phone;
    private String birthdate;
    private String country;
    private String state;


    @Override
    public String toString() {
        return "Users{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Users(String firstname, String lastname, String age, String email, String phone, String birthdate, String country, String state) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.country = country;
        this.state = state;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
