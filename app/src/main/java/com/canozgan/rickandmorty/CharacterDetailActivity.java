package com.canozgan.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.canozgan.rickandmorty.databinding.ActivityCharacterDetailBinding;
import com.squareup.picasso.Picasso;

public class CharacterDetailActivity extends AppCompatActivity {
    ActivityCharacterDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCharacterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Singleton singleton=Singleton.getInstance();
        CharacterModel selectedCharacterModel= singleton.selectedCharacterModel;
        binding.titleTextView.setText(selectedCharacterModel.name);
        binding.statusTextView.setText(selectedCharacterModel.status);
        binding.specyTextView.setText(selectedCharacterModel.specy);
        binding.genderTextView.setText(selectedCharacterModel.gender);
        binding.originTextView.setText(selectedCharacterModel.origin.originName);
        binding.locationTextView.setText(selectedCharacterModel.location.locationName);
        binding.createdAtTextView.setText(selectedCharacterModel.createdAt);
        if(selectedCharacterModel.episodeUrls.size()!=0){
            String BASE_URL_FOR_EPISODES="https://rickandmortyapi.com/api/episode/";
            String Episodes ="";
            for(String episodeUrl : singleton.selectedCharacterModel.episodeUrls){
                String episodeId ="";
                for(int i=BASE_URL_FOR_EPISODES.length();i<episodeUrl.length();i++){
                    episodeId= episodeId+ episodeUrl.charAt(i);
                }
                episodeId=episodeId+", ";
                Episodes=Episodes+episodeId;
            }
            Episodes=Episodes.substring(0,Episodes.length()-2);
            binding.episodesTextView.setText(Episodes);
        }else{
            binding.episodesTextView.setText("no episode");
        }
        try {
            Picasso.get().load(selectedCharacterModel.imageUrl).into(binding.imageView2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}