package com.example.prince.hackathon.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prince on 3/26/17.
 */

public class User {

    /**
     * @Expose
     * @SerializerName = Attributes coming from API
     */
    @Expose
    @SerializedName("examname")
    private String examname;
    @Expose
    @SerializedName("admitno")
    private String admitno;
    @Expose
    @SerializedName("latitude")
    private String latitude;
    @Expose
    @SerializedName("longitude")
    private String longitude;
    @Expose
    @SerializedName("centername")
    private String centername;


    public User(String examname, String admitno, String latitude, String longitude, String centername) {
        this.examname = examname;
        this.admitno = admitno;
        this.latitude = latitude;
        this.longitude = longitude;
        this.centername = centername;
    }

    /**
     * @param userJson
     * @return getInstance() takes Json string & return User.class
     */
    public static User getInstance(String userJson) {
        return (new Gson()).fromJson(userJson, User.class);

    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }

    public String getAdmitno() {
        return admitno;
    }

    public void setAdmitno(String admitno) {
        this.admitno = admitno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }
}
