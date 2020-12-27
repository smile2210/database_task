package com.example.mytaskdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytaskdatabase.Database.Task2DataBase;
import com.example.mytaskdatabase.Database.Task3DataBase;

import java.util.ArrayList;
import java.util.List;

public class Task3Activity extends AppCompatActivity {
    EditText id, name, surname;
    Button back;
    Button insert;
    RecyclerView task3data;
    List<Data3Model> modellist3 = new ArrayList<>();
    Task3DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        back = findViewById(R.id.back);
        insert = findViewById(R.id.insert3);
        task3data = findViewById(R.id.task3data);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dataBase = new Task3DataBase(Task3Activity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String surname1 = surname.getText().toString();

                if (name1.isEmpty() && surname1.isEmpty()){
                    Toast.makeText(Task3Activity.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
                }else{
                    dataBase.InserData(name1,surname1);
                    recycle();

                    name.getText().clear();
                    surname.getText().clear();
                }
            }
        });
    }

    public void recycle() {

        modellist3.clear();
        List<Data3Model> data3Models = dataBase.RetriveData();
        for (int i=0; i<data3Models.size(); i++){
            Data3Model model = new Data3Model();
            model.setId(data3Models.get(i).getId());
            model.setName(data3Models.get(i).getName());
            model.setSurname(data3Models.get(i).getSurname());
            modellist3.add(model);
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(Task3Activity.this,RecyclerView.VERTICAL,false);
        Data3Adapter adapter = new Data3Adapter(Task3Activity.this,modellist3,dataBase);
        task3data.setLayoutManager(manager);
        task3data.setAdapter(adapter);
    }
}