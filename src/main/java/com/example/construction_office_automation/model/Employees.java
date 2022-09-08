package com.example.construction_office_automation.model;

public class Employees {
    private int id;
    private String firstName;
    private String surname;
    private String otherNames;
    private String email;
    private String Department;
    private long PhoneNumber;
    private int age;
    private String gender;
    private String role;
    private String imgUrl;


    public Employees(){};
    public Employees(String firstName, String surname, String otherNames, String email, String department, long phoneNumber, int age, String gender) {
        this.firstName = firstName;
        this.surname = surname;
        this.otherNames = otherNames;
        this.email = email;
        Department = department;
        PhoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String username) {
        this.surname = username;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean validateFields(){
        if(
               getFirstName() != null
               &&  getEmail() != null
               && getSurname() != null
               && getOtherNames()!= null
               && getDepartment() != null
               && getGender() != null
               && getRole() != null
               && getImgUrl() != null
                && getAge() != 0
               && getPhoneNumber() != 0

        ) return true;

        return false;
    }


}
