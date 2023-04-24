package com.canozgan.rickandmorty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import com.canozgan.rickandmorty.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Singleton singleton;
    private String BASE_URL_FOR_LOCATIONS;
    private String BASE_URL_FOR_CHARACTERS;
    VerticalRecyclerViewAdapter verticalRecyclerViewAdapter;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        singleton =Singleton.getInstance();

        if(singleton.locationArrayList.size()==0){
            loadDataHorizontalList();
        }
        else{
            if(singleton.location!=null){
                singleton.locationArrayList.remove(singleton.location);
                singleton.locationArrayList.add(0,singleton.location);
                singleton.locationArrayList.get(0).isLatestClicked=true;
            }
            binding.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
            binding.horizontalRecyclerView.setAdapter(singleton.horizontalRecyclerViewAdapter);
            loadDataVerticalList();
        }

        initScrollListener();


    }
    private void loadDataHorizontalList(){
        singleton.isLoading=true;
        BASE_URL_FOR_LOCATIONS ="https://rickandmortyapi.com/api/";
        String locationUrl="location?page="+singleton.pageNumber;
        singleton.pageNumber++;
        Gson gson= new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FOR_LOCATIONS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiForModels apiForModels = retrofit.create(ApiForModels.class);
        Call<LocationModel> call = apiForModels.getDataForLocations(locationUrl);

        call.enqueue(new Callback<LocationModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                if(response.isSuccessful()){
                    LocationModel locationModel=response.body();
                    singleton.locationArrayList=locationModel.locationArrayList;
                    singleton.locationArrayList.get(0).isLatestClicked=true;
                    singleton.location= singleton.locationArrayList.get(0);
                    singleton.horizontalRecyclerViewAdapter=new HorizontalRecyclerViewAdapter(singleton.locationArrayList);
                    binding.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    binding.horizontalRecyclerView.setAdapter(singleton.horizontalRecyclerViewAdapter);
                    singleton.isLoading=false;
                    loadDataVerticalList();
                }
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                singleton.isLoading=false;
            }
        });

    }
    private void initScrollListener() {
        binding.horizontalRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!singleton.isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == singleton.locationArrayList.size() - 1) {
                        loadMoreHorizontalList();
                    }
                }
            }
        });
    }
    public void loadMoreHorizontalList(){
        if(singleton.pageNumber<=7){
            singleton.isLoading = true;
            singleton.locationArrayList.add(null);
            singleton.horizontalRecyclerViewAdapter.notifyItemInserted(singleton.locationArrayList.size() - 1);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void run() {
                    singleton.locationArrayList.remove(singleton.locationArrayList.size() - 1);
                    int scrollPosition = singleton.locationArrayList.size();
                    singleton.horizontalRecyclerViewAdapter.notifyItemRemoved(scrollPosition);
                    BASE_URL_FOR_LOCATIONS ="https://rickandmortyapi.com/api/";
                    String locationUrl="location?page="+singleton.pageNumber;
                    singleton.pageNumber++;
                    Gson gson= new GsonBuilder().setLenient().create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL_FOR_LOCATIONS)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    ApiForModels apiForModels = retrofit.create(ApiForModels.class);
                    Call<LocationModel> call = apiForModels.getDataForLocations(locationUrl);

                    call.enqueue(new Callback<LocationModel>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                            if(response.isSuccessful()){
                                LocationModel locationModel=response.body();
                                for (Location location : locationModel.locationArrayList) {
                                    singleton.locationArrayList.add(location);
                                }
                                singleton.isLoading=false;
                                singleton.horizontalRecyclerViewAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<LocationModel> call, Throwable t) {
                            singleton.isLoading=false;
                        }
                    });
                }
            }, 2000);
        }

    }
    public void loadDataVerticalList(){
        if(singleton.location.urlsForCharacters.size()!=0){
            BASE_URL_FOR_CHARACTERS="https://rickandmortyapi.com/api/character/";
            String ADDITIONAL_URL_FOR_CHARACTERS ="";
            for(String characterUrl : singleton.location.urlsForCharacters){
                String characterId ="";
                for(int i=BASE_URL_FOR_CHARACTERS.length();i<characterUrl.length();i++){
                    characterId= characterId+ characterUrl.charAt(i);
                }
                characterId=characterId+",";
                ADDITIONAL_URL_FOR_CHARACTERS=ADDITIONAL_URL_FOR_CHARACTERS+characterId;
            }
            Gson gson= new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_FOR_CHARACTERS)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ApiForModels apiForModels = retrofit.create(ApiForModels.class);
            Call<List<CharacterModel>> call = apiForModels.getDataForCharacters(ADDITIONAL_URL_FOR_CHARACTERS);
            call.enqueue(new Callback<List<CharacterModel>>() {
                @Override
                public void onResponse(Call<List<CharacterModel>> call, Response<List<CharacterModel>> response) {
                    if(response.isSuccessful()){
                        ArrayList<CharacterModel> characterModelArrayList=new ArrayList<>(response.body());
                        verticalRecyclerViewAdapter=new VerticalRecyclerViewAdapter(characterModelArrayList);
                        binding.verticalRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        binding.verticalRecyclerView.setAdapter(verticalRecyclerViewAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<CharacterModel>> call, Throwable t) {

                }
            });
        }
    }


}