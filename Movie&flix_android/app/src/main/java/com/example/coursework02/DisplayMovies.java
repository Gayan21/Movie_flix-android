package com.example.coursework02;


import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMovies extends AppCompatActivity {//https://youtu.be/9t8VVWebRFM
    DataBaseHelper dataBase;
    Button addBtn;
    ListView listView;
    String favN="ok";
    ArrayAdapter<String> adapter;
    ArrayList<String> arrList = new ArrayList<>();//create arraylist check items
    ArrayList<String> arrFavList = new ArrayList<>();//create arraylist for favourite items
    String[] strArray = new String[arrList.size()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
        listView = findViewById(R.id.movieList);
        addBtn = findViewById(R.id.addToFavBtn);
        dataBase = new DataBaseHelper(this);

        Cursor res = dataBase.getData();
        if (res.getCount() == 0) {
            Toast.makeText(DisplayMovies.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

       // StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            String count = res.getString(0);
            arrList.add(count);
        }

        strArray = arrList.toArray(strArray);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,strArray);
        listView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(favN);
                for(int i =0; i<arrFavList.size();i++){
                    dataBase.updateF(arrFavList.get(i),favN);
                    Toast.makeText(DisplayMovies.this, "added to favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = ((TextView)view).getText().toString();
                if(arrFavList.contains(selected)){
                    arrFavList.remove(selected);
                }else{
                    arrFavList.add(selected);
                }

            }
        });
    }

}