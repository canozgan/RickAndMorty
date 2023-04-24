package com.canozgan.rickandmorty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
class Origin{
    @SerializedName("name")
    @Expose
    String originName;
}
class LocationForCharacterModel{
    @SerializedName("name")
    @Expose
    String locationName;
}
public class CharacterModel {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("image")
    @Expose
    String imageUrl;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("species")
    @Expose
    String specy;
    @SerializedName("gender")
    @Expose
    String gender;
    @SerializedName("origin")
    @Expose
    Origin origin;
    @SerializedName("location")
    @Expose
    LocationForCharacterModel location;
    @SerializedName("episode")
    @Expose
    ArrayList<String> episodeUrls;
    @SerializedName("created")
    @Expose
    String createdAt;


}
