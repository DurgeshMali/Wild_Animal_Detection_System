package com.example.wild_animal_detection_system;

public class Users {
    String name, phoneNo, password;
    String camName, camIP;

    public Users(String camName, String camIP) {
        this.camName = camName;
        this.camIP = camIP;
    }

    public Users(String name, String phoneNo, String password) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCamName() {
        return camName;
    }

    public void setCamName(String camName) {
        this.camName = camName;
    }

    public String getCamIP() {
        return camIP;
    }

    public void setCamIP(String camIP) {
        this.camIP = camIP;
    }
}
