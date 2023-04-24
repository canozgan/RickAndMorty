package com.canozgan.rickandmorty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canozgan.rickandmorty.databinding.FemaleItemBinding;
import com.canozgan.rickandmorty.databinding.MaleItemBinding;
import com.canozgan.rickandmorty.databinding.UnknownItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_MALE = 0;
    private final int VIEW_TYPE_FEMALE = 1;
    private final int VIEW_TYPE_UNKNOWN = 2;
    ArrayList<CharacterModel> characterModelArrayList;

    public VerticalRecyclerViewAdapter(ArrayList<CharacterModel> characterModelArrayList) {
        this.characterModelArrayList = characterModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_MALE){
            MaleItemBinding binding=MaleItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new Male_Item_Holder(binding);
        }else if(viewType==VIEW_TYPE_FEMALE){
            FemaleItemBinding binding=FemaleItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new Female_Item_Holder(binding);
        }else{
            UnknownItemBinding binding=UnknownItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new Unknown_Item_Holder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(holder instanceof Male_Item_Holder){
            ((Male_Item_Holder) holder).binding.recyclerViewTextView.setText(characterModelArrayList.get(position).name);
            try {
                Picasso.get().load(characterModelArrayList.get(position).imageUrl).into(((Male_Item_Holder) holder).binding.recyclerViewImageView);
            }catch (Exception e){
                e.printStackTrace();
            }
            ((Male_Item_Holder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton singleton=Singleton.getInstance();
                    singleton.selectedCharacterModel=characterModelArrayList.get(position);
                    Intent intentToDetailActivity=new Intent(((Male_Item_Holder) holder).itemView.getContext(),CharacterDetailActivity.class);
                    v.getContext().startActivity(intentToDetailActivity);
                }
            });
        }else if(holder instanceof Female_Item_Holder){
            ((Female_Item_Holder) holder).binding.recyclerViewTextView.setText(characterModelArrayList.get(position).name);
            try {
                Picasso.get().load(characterModelArrayList.get(position).imageUrl).into(((Female_Item_Holder) holder).binding.recyclerViewImageView);
            }catch (Exception e){
                e.printStackTrace();
            }
            ((Female_Item_Holder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton singleton=Singleton.getInstance();
                    singleton.selectedCharacterModel=characterModelArrayList.get(position);
                    Intent intentToDetailActivity=new Intent(((Female_Item_Holder) holder).itemView.getContext(),CharacterDetailActivity.class);
                    v.getContext().startActivity(intentToDetailActivity);
                }
            });
        }else{
            ((Unknown_Item_Holder) holder).binding.recyclerViewTextView.setText(characterModelArrayList.get(position).name);
            try {
                Picasso.get().load(characterModelArrayList.get(position).imageUrl).into(((Unknown_Item_Holder) holder).binding.recyclerViewImageView);
            }catch (Exception e){
                e.printStackTrace();
            }
            ((Unknown_Item_Holder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton singleton=Singleton.getInstance();
                    singleton.selectedCharacterModel=characterModelArrayList.get(position);
                    Intent intentToDetailActivity=new Intent(((Unknown_Item_Holder) holder).itemView.getContext(),CharacterDetailActivity.class);
                    v.getContext().startActivity(intentToDetailActivity);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return characterModelArrayList.size();
    }
    @Override
    public int getItemViewType(int position) {
        if(characterModelArrayList.get(position).gender.matches("Male")){
            return  VIEW_TYPE_MALE;
        }
        else if(characterModelArrayList.get(position).gender.matches("Female")){
            return VIEW_TYPE_FEMALE;
        }else{
            return VIEW_TYPE_UNKNOWN;
        }
    }
    public class Male_Item_Holder extends RecyclerView.ViewHolder{
        private MaleItemBinding binding;
        public Male_Item_Holder(MaleItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    public class Female_Item_Holder extends RecyclerView.ViewHolder{
        private FemaleItemBinding binding;
        public Female_Item_Holder(FemaleItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    public class Unknown_Item_Holder extends RecyclerView.ViewHolder{
        private UnknownItemBinding binding;
        public Unknown_Item_Holder(UnknownItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
