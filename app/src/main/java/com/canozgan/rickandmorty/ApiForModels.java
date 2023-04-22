package com.canozgan.rickandmorty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiForModels {
    @GET
    Call<LocationModel> getData(@Url String url);
}

