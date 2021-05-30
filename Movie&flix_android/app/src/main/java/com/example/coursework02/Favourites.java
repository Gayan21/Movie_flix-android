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

public class  Favourites extends AppCompatActivity { //https://youtu.be/9t8VVWebRFM


    ArrayAdapter<String> adapter;
    ArrayList<String> arrList = new ArrayList<>();
    ArrayList<String> arrFavList = new ArrayList<>();
    String[] strArray = new String[arrList.size()];
    DataBaseHelper dataBase;
    Button saveBtn;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listView = findViewById(R.id.movieListFav);
        saveBtn= findViewById(R.id.addToFavBtnFav);
        dataBase = new DataBaseHelper(this);
        Cursor res = dataBase.getData();
        if (res.getCount() == 0) {
            Toast.makeText(Favourites.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {

            String count = res.getString(0);
            String fav = res.getString(6);
            if (fav.equals("ok")) {
                arrList.add(count);
            }
        }

        strArray = arrList.toArray(strArray);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, strArray);
        listView.setAdapter(adapter);

        for(int k=0;k<strArray.length;k++){
            listView.setItemChecked(k,true);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0; i<arrFavList.size();i++){
                    dataBase.updateF(arrFavList.get(i),"Not ok");
                    Toast.makeText(Favourites.this, "saved successfully", Toast.LENGTH_SHORT).show();
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