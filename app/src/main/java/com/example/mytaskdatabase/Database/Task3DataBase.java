package com.example.mytaskdatabase.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mytaskdatabase.Data3Model;

import java.util.ArrayList;
import java.util.List;

public class Task3DataBase extends SQLiteOpenHelper {
    Context context;
    public Task3DataBase(@Nullable Context context) {
        super(context, "TASK3.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query3 = "CREATE TABLE table3(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT)";
        sqLiteDatabase.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InserData(String name, String surname){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("surname", surname);
        long chack = database.insert("table3", null, values);
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
        long chackupdate = database.update("table3",contentValues,"id="+id,null);

        if (chackupdate == 1){
            Toast.makeText(context, "Sucessfully update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteData(String id){
        SQLiteDatabase database = getWritableDatabase();
        long chackdelete = database.delete("table3","id="+id,null);

        if (chackdelete == 1){
            Toast.makeText(context, "Delete Sucessfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Delete fail", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Data3Model> RetriveData(){

        List<Data3Model> modellist3 = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM table3";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();

        for (int i=0; i<cursor.getCount(); i++){
            String ids = cursor.getString(0);
            String names = cursor.getString(1);
            String surnames = cursor.getString(2);

            Data3Model model3 = new Data3Model(ids,names,surnames);
            modellist3.add(model3);
            cursor.moveToNext();
        }

        return modellist3;
    }

}
