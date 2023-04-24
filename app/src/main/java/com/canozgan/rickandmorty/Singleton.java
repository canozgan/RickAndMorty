package com.canozgan.rickandmorty;

import java.util.ArrayList;

public class Singleton {
    public ArrayList<Location> locationArrayList;
    public Location location;
    public int pageNumber;
    public boolean isLoading;
    HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter;
    public CharacterModel selectedCharacterModel;
    private static Singleton singleton;
    private Singleton(){
        locationArrayList =new ArrayList<>();
        pageNumber=1;
        isLoading =false;
    }
    public static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
            return singleton;
        }
        else{
            return singleton;
        }
    }
}
