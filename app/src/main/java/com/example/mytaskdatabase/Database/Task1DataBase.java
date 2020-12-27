package com.example.mytaskdatabase.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mytaskdatabase.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Task1DataBase extends SQLiteOpenHelper {
    Context context;

    public Task1DataBase(@Nullable Context context) {
        super(context, "TASK1.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query1 = "CREATE TABLE table1(id INTEGER PRIMARY KEY, name TEXT, surname TEXT)";
        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Insertdata(String id, String name, String surname){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("surname",surname);
       long chack = database.insert("table1",null,contentValues);

       if (chack == -1){
           Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Sucess", Toast.LENGTH_SHORT).show();
       }

    }

    public void UpdateData(String id, String name, String surname){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("surname",surname);
        long chackupdate = database.update("table1",values,"id="+id,null);

        if (chackupdate == 1){
            Toast.makeText(context, "Sucessfully update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteData(String id){
        SQLiteDatabase database = getWritableDatabase();
        long chackdelete = database.delete("table1","id="+id,null);

        if (chackdelete == 1){
            Toast.makeText(context, "Delete Sucessfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Delete fail", Toast.LENGTH_SHORT).show();
        }
    }

    public List<DataModel> RetriveData(){

        List<DataModel> modelList = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM table1";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();

        for (int i=0; i<cursor.getCount(); i++){
            String ids = cursor.getString(0);
            String names = cursor.getString(1);
            String surnames = cursor.getString(2);

            DataModel model = new DataModel(ids,names,surnames);
            modelList.add(model);
            cursor.moveToNext();
        }

        return modelList;
    }
}
