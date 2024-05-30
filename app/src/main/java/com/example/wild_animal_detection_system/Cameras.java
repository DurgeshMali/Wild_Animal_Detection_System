package com.example.wild_animal_detection_system;

public class Cameras {
    private String key; // Use key as a unique identifier
    private String camName;
    private String camIP;

    public Cameras() {
    }

    public Cameras(String key, String camName, String camIP) {
        this.key = key;
        this.camName = camName;
        this.camIP = camIP;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
