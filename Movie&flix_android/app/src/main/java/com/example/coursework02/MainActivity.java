package com.example.coursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button regMovieBtn;
    Button displayMovieBtn;
    Button favouritesBtn;
    Button editMovieBtn;
    Button searchBtn;
    Button ratingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regMovieBtn = findViewById(R.id.regMovieId);

        regMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterMovie.class);
                startActivity(intent);
            }
        });
        displayMovieBtn = findViewById(R.id.displayMovieID);
        displayMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayMovies.class);
                startActivity(intent);
            }
        });

        favouritesBtn = findViewById(R.id.favouritesId);
        favouritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Favourites.class);
                startActivity(intent);
            }
        });
        editMovieBtn = findViewById(R.id.editMoviesId);
        editMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditMovies.class);
                startActivity(intent);
            }
        });
        searchBtn = findViewById(R.id.searchId);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });
        ratingBtn = findViewById(R.id.ratingsId);
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ratings.class);
                startActivity(intent);
            }
        });
    }
}