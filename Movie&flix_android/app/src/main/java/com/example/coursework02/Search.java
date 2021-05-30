package com.example.coursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {    //https://developer.android.com/guide/topics/search/search-dialog#SearchingYourData

    ArrayList<String> arrList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String[] strArray = new String[arrList.size()];
    Button btn;
    DataBaseHelper dataBase;
    SearchView searchView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.search_list);
        btn = findViewById(R.id.mSearchId);


        arrList.clear();
        dataBase = new DataBaseHelper(Search.this);
        Cursor res = dataBase.getData();
        while (res.moveToNext()) {
            String count = res.getString(0);
            arrList.add(count);
        }
        strArray = arrList.toArray(strArray);
        adapter = new ArrayAdapter<String>(Search.this,android.R.layout.simple_list_item_1,strArray);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Search.this,btn);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.movieName:
                                arrList.clear();
                                dataBase = new DataBaseHelper(Search.this);
                                Cursor res = dataBase.getData();
                                while (res.moveToNext()) {
                                    String count = res.getString(0);
                                    arrList.add(count);
                                }
                                strArray = arrList.toArray(strArray);
                                adapter = new ArrayAdapter<String>(Search.this,android.R.layout.simple_list_item_1,strArray);
                                listView.setAdapter(adapter);
                                break;

                            case R.id.movieActors:
                                arrList.clear();
                                dataBase = new DataBaseHelper(Search.this);
                                res = dataBase.getData();
                                while (res.moveToNext()) {
                                    String count = res.getString(3);
                                    arrList.add(count);
                                }
                                strArray = arrList.toArray(strArray);
                                adapter = new ArrayAdapter<String>(Search.this,android.R.layout.simple_list_item_1,strArray);
                                listView.setAdapter(adapter);
                                break;

                            case R.id.movieDirector:
                                arrList.clear();
                                dataBase = new DataBaseHelper(Search.this);
                                res = dataBase.getData();
                                while (res.moveToNext()) {
                                    String count = res.getString(2);
                                    arrList.add(count);
                                }
                                strArray = arrList.toArray(strArray);
                                adapter = new ArrayAdapter<String>(Search.this,android.R.layout.simple_list_item_1,strArray);
                                listView.setAdapter(adapter);
                                break;

                            case R.id.movieReview:
                                arrList.clear();
                                dataBase = new DataBaseHelper(Search.this);res = dataBase.getData();
                                while (res.moveToNext()) {
                                    String count = res.getString(5);
                                    arrList.add(count);
                                }
                                strArray = arrList.toArray(strArray);
                                adapter = new ArrayAdapter<String>(Search.this,android.R.layout.simple_list_item_1,strArray);
                                listView.setAdapter(adapter);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}