package com.example.coursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMovies extends AppCompatActivity { //https://youtu.be/wK-JccC-i4Y

    DataBaseHelper dataBase;
    ListView listView;
    ArrayList<String> arrList = new ArrayList<>();//arraylist fro movielist
    String[] strArray = new String[arrList.size()];
    ArrayAdapter<String> adapter;
    private Context context;
    private Activity activity;
    String  movieName, movieYear,  movieDirector ,  movieActor ,  movieRating , movieReview,favourites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);

        dataBase = new DataBaseHelper(this);
        listView=findViewById(R.id.movieListEdit);



      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              movieName = arrList.get(position);
              Cursor res = dataBase.getData();
              while (res.moveToNext()) {
                  String name2 = res.getString(0);
                  if (movieName.equals(name2)) {
                      movieYear = res.getString(1);
                      movieDirector= res.getString(2);
                      movieActor = res.getString(3);
                      movieRating = res.getString(4);
                      movieReview = res.getString(5);
                      favourites = res.getString(6);
                  }
              }
              Intent intent = new Intent(EditMovies.this, UpdateActivity.class);
              intent.putExtra("Name",  movieName);
                intent.putExtra("Year", movieYear);
                intent.putExtra("Director", movieDirector);
                intent.putExtra("Act", movieActor);
              intent.putExtra("Rating", movieRating);
              intent.putExtra("Review", movieReview);
              intent.putExtra("Fav", favourites);

              startActivity(intent);
          }
      });

        Cursor res = dataBase.getData();
        if (res.getCount() == 0) {
            Toast.makeText(EditMovies.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }




        while (res.moveToNext()) {
            String count = res.getString(0);
            arrList.add(count);
        }

        strArray = arrList.toArray(strArray);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,strArray);
        listView.setAdapter(adapter);
    }
}