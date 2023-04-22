package com.canozgan.rickandmorty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

class Location {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("residents")
    @Expose
    public ArrayList<String> urlsForCharacters;
    boolean isLatestClicked = false;
}

public class LocationModel {
    @SerializedName("results")
    @Expose
    public ArrayList<Location> locationArrayList;

}

