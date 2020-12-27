package com.example.mytaskdatabase.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mytaskdatabase.Data2Model;

import java.util.ArrayList;
import java.util.List;

public class Task2DataBase extends SQLiteOpenHelper {
    Context context;

    public Task2DataBase(@Nullable Context context) {
        super(context, "TASK2.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query2 = "CREATE TABLE table2(id INTEGER PRIMARY KEY, name TEXT, surname TEXT)";
        sqLiteDatabase.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InsertData(String id, String name, String surname) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("surname", surname);
        long chack = database.insert("table2", null, values);
        if (chack == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateData(String id, String name, String surname){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("surname",surname);
        long chackupdate = database.update("table2",contentValues,"id="+id,null);

        if (chackupdate == 1){
            Toast.makeText(context, "Sucessfully update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteData(String id){
        SQLiteDatabase database = getWritableDatabase();
        long chackdelete = database.delete("table2","id="+id,null);

        if (chackdelete == 1){
            Toast.makeText(context, "Delete Sucessfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Delete fail", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Data2Model> RetriveData(){

        List<Data2Model> modellist2 = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM table2";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();

        for (int i=0; i<cursor.getCount(); i++){
            String ids = cursor.getString(0);
            String names = cursor.getString(1);
            String surnames = cursor.getString(2);

            Data2Model model2 = new Data2Model(ids,names,surnames);
            modellist2.add(model2);
            cursor.moveToNext();
        }

        return modellist2;
    }
}
