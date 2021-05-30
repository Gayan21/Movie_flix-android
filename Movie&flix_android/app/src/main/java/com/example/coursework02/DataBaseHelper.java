package com.example.coursework02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;//https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase
    public DataBaseHelper(Context context) {
        super(context, "MovieData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DataBase) {
        DataBase.execSQL("create Table MovieData(movieName TEXT primary key, movieYear TEXT, movieDirector TEXT, actList TEXT,rating TEXT, review TEXT,Fav TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DataBase, int oldVersion, int newVersion) {
        DataBase.execSQL("drop Table if exists MovieData");
    }

    public Boolean insert(String movieName ,int movieYear , String movieDirector , String actList ,int rating , String review, String fav ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("movieName",movieName);
        cv.put("movieYear",movieYear);
        cv.put("movieDirector",movieDirector);
        cv.put("actList",actList);
        cv.put("rating",rating);
        cv.put("review",review);
        cv.put("fav",fav);
        long result=DB.insert("movieData",null,cv);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateF(String movieName ,String fav ){
        SQLiteDatabase DataBase = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("fav",fav);
        Cursor cursor =DataBase.rawQuery("Select * from MovieData where movieName = ?",new String[]{movieName});
        if(cursor.getCount()>0){
            long result=DataBase.update("movieData",cv,"movieName = ?",new String []{movieName});
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DataBase = this.getWritableDatabase();
        Cursor cursor =DataBase.rawQuery("Select * from MovieData order by movieName",null);
        return cursor;
    }
    public Boolean updateDetails(String movieName ,int movieYear , String movieDirector , String actList ,int rating , String review ){
        SQLiteDatabase DataBase = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("movieYear",movieYear);
        cv.put("movieDirector",movieDirector);
        cv.put("actList",actList);
        cv.put("rating",rating);
        cv.put("review",review);
        Cursor cursor =DataBase.rawQuery("Select * from MovieData where movieName = ?",new String[]{movieName});
        if(cursor.getCount()>0){
            long result=DataBase.update("movieData",cv,"movieName = ?",new String []{movieName});
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }else{
            return false;
        }
    }
}



