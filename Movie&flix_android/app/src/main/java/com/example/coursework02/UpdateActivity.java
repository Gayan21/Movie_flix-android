package com.example.coursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {//https://youtu.be/wK-JccC-i4Y
    EditText movieName,movieYear,movieDirector,movieAct,movieRating,movieReview;
    Button updateBtn;
    DataBaseHelper dataBase;
    String Name,Year,Director,Act,Rating,Review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movieName=findViewById(R.id.mnameId2);
        movieYear=findViewById(R.id.myearId2);
        movieDirector=findViewById(R.id.mdirectorId2);
        movieAct=findViewById(R.id.mactId2);
        movieRating=findViewById(R.id.mratingId2);
        movieReview=findViewById(R.id.mreviewId2);
        updateBtn=findViewById(R.id.mUpdateId);
        getAndSetIntentData();
        dataBase = new DataBaseHelper(this);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this

                String movieNameInsert=movieName.getText().toString();
                int movieYearInsert=Integer.parseInt(movieYear.getText().toString());
                String movieDirectorInsert=movieDirector.getText().toString();
                String movieActInsert=movieAct.getText().toString();
                int movieRatingInsert=Integer.parseInt(movieRating.getText().toString());
                String movieReviewInsert=movieReview.getText().toString();
                dataBase.updateDetails(movieNameInsert, movieYearInsert, movieDirectorInsert, movieActInsert,movieRatingInsert,movieReviewInsert);
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("Name") && getIntent().hasExtra("Year") &&
                getIntent().hasExtra("Director") && getIntent().hasExtra("Act") && getIntent().hasExtra("Rating") && getIntent().hasExtra("Review")){
            //Getting Data from Intent
            Name = getIntent().getStringExtra("Name");
            Year = getIntent().getStringExtra("Year");
            Director = getIntent().getStringExtra("Director");
            Act = getIntent().getStringExtra("Act");
            Rating = getIntent().getStringExtra("Rating");
            Review = getIntent().getStringExtra("Review");

            //Setting Intent Data
           movieName.setText( Name);
            movieYear.setText(Year);
            movieDirector.setText(Director);
            movieAct.setText(Act);
            movieRating.setText(Rating);
            movieReview.setText(Review);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}