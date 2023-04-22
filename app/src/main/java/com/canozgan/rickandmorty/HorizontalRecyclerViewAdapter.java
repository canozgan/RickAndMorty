package com.canozgan.rickandmorty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canozgan.rickandmorty.databinding.ClickedLocationItemBinding;
import com.canozgan.rickandmorty.databinding.LazyLoadingItemBinding;
import com.canozgan.rickandmorty.databinding.LocationItemBinding;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final int VIEW_TYPE_CLICKED = 2;
    public ArrayList<Location> locationArrayList;

    public HorizontalRecyclerViewAdapter(ArrayList<Location> locationArrayList) {
        this.locationArrayList = locationArrayList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            LocationItemBinding binding=LocationItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new LocationItemHolder(binding);
        } else if(viewType== VIEW_TYPE_LOADING) {
            LazyLoadingItemBinding binding=LazyLoadingItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new LazyLoadingItemHolder(binding);
        }else{
            ClickedLocationItemBinding binding = ClickedLocationItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new ClickedLocationItemHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder instanceof LocationItemHolder) {
            ((LocationItemHolder) holder).binding.recyclerViewTextView.setText(locationArrayList.get(position).name);

            ((LocationItemHolder) holder).binding.recyclerViewTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Singleton singleton =Singleton.getInstance();
                    for(int i=0; i<locationArrayList.size();i++){
                        if(locationArrayList.get(i)!=null){
                            locationArrayList.get(i).isLatestClicked=false;
                        }
                    }
                    singleton.location=locationArrayList.get(position);
                    singleton.locationArrayList=locationArrayList;
                    Intent intent =new Intent(v.getContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    v.getContext().startActivity(intent);

                }
            });
        } else if (holder instanceof ClickedLocationItemHolder) {
            ((ClickedLocationItemHolder) holder).binding.recyclerViewTextView.setText(locationArrayList.get(position).name);

            ((ClickedLocationItemHolder) holder).binding.recyclerViewTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Singleton singleton =Singleton.getInstance();
                    for(Location location : locationArrayList){
                        if(location!=null){
                            location.isLatestClicked=false;
                        }
                    }
                    singleton.location=locationArrayList.get(position);
                    singleton.locationArrayList=locationArrayList;
                    Intent intent =new Intent(v.getContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return locationArrayList.size();
    }
    @Override
    public int getItemViewType(int position) {
        if(locationArrayList.get(position)==null){
            return  VIEW_TYPE_LOADING;
        }
        else if(locationArrayList.get(position).isLatestClicked){
            return VIEW_TYPE_CLICKED;
        }else{
            return VIEW_TYPE_ITEM;
        }
    }

    public class LocationItemHolder extends RecyclerView.ViewHolder{

        private LocationItemBinding binding;
        public LocationItemHolder(LocationItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    public class ClickedLocationItemHolder extends RecyclerView.ViewHolder{

        private ClickedLocationItemBinding binding;
        public ClickedLocationItemHolder(ClickedLocationItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public class LazyLoadingItemHolder extends RecyclerView.ViewHolder{

        private LazyLoadingItemBinding binding;
        public LazyLoadingItemHolder(LazyLoadingItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
