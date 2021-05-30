package com.example.coursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity { //https://youtu.be/9t8VVWebRFM

    EditText movieName,movieYear,movieDirector,movieAct,movieRating,movieReview;
    Button saveBtn;
    DataBaseHelper dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        movieName=findViewById(R.id.mnameId);
        movieYear=findViewById(R.id.myearId);
        movieDirector=findViewById(R.id.mdirectorId);
        movieAct=findViewById(R.id.mactId);
        movieRating=findViewById(R.id.mratingId);
        movieReview=findViewById(R.id.mreviewId);
        saveBtn=findViewById(R.id.msaveId);
        dataBase = new DataBaseHelper(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieNameInsert=movieName.getText().toString();
                int movieYearInsert=Integer.parseInt(movieYear.getText().toString());
                String movieDirectorInsert=movieDirector.getText().toString();
                String movieActInsert=movieAct.getText().toString();
                int movieRatingInsert=Integer.parseInt(movieRating.getText().toString());
                String movieReviewInsert=movieReview.getText().toString();

                String f="Not ok";

                if(movieYearInsert>1895){
                    if(movieRatingInsert>=1){
                        if(movieRatingInsert<=10){
                            dataBase.insert(movieNameInsert,movieYearInsert,movieDirectorInsert,movieActInsert,movieRatingInsert,movieReviewInsert,f);
                            Toast toast=Toast.makeText(getApplicationContext(),"Movie Added",Toast.LENGTH_SHORT);

                            toast.show();
                        }
                        else{
                            Toast toast=Toast.makeText(getApplicationContext(),"rating 1-10",Toast.LENGTH_SHORT);

                            toast.show();
                        }
                    }else {
                        Toast toast=Toast.makeText(getApplicationContext(),"rating 1-10",Toast.LENGTH_SHORT);

                        toast.show();
                    }
                }else {
                    Toast toast=Toast.makeText(getApplicationContext(),"Invalid Year",Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
    }
}