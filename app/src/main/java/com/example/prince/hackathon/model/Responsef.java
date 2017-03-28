package com.example.prince.hackathon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prince on 3/27/17.
 */

public class Responsef {

    @Expose
    @SerializedName("name")
    private String name;

    public Responsef() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
