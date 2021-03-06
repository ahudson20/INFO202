/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author anaruhudson
 */
public class Customer {
    private Integer personID;
    private String username;
    private String firstname;
    private String surname;
    private String password;
    private String emailAddress;
    private String shippingAddress;
    private String creditCardDetails;
    
    public Customer(){}
    
    public Customer(Integer personID, String username, String firstname, String surname, String password, String emailAddress, String shippingAddress, String creditCardDetails) {
        this.personID = personID;
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.emailAddress = emailAddress;
        this.shippingAddress = shippingAddress;
        this.creditCardDetails = creditCardDetails;
    }

    public Integer getPersonID() {
        return personID;
    }

    public String getUserName() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCreditCardDetails(String creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }
    
    @Override
    public String toString(){
        return "First Name: " + this.firstname + " Last Name: " + this.surname;
    }
}
