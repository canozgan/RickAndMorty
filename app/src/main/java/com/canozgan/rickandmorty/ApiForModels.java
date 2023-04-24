package com.canozgan.rickandmorty;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiForModels {
    @GET
    Call<LocationModel> getDataForLocations(@Url String url);
    @GET
    Call<List<CharacterModel>> getDataForCharacters(@Url String url);

}