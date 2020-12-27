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

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Task2Activity extends AppCompatActivity {
    EditText id, name, surname;
    Button back;
    Button insert, update, delete;
    RecyclerView datashow;
    List<Data2Model> modellist2 = new ArrayList<>();
    Task2DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        back = findViewById(R.id.back);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        datashow = findViewById(R.id.datashow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dataBase = new Task2DataBase(Task2Activity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                String surname1 = surname.getText().toString();

                if (id1.isEmpty() && name1.isEmpty() && surname1.isEmpty()) {
                    Toast.makeText(Task2Activity.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.InsertData(id1, name1, surname1);
                    recycle();

                }

                id.getText().clear();
                name.getText().clear();
                surname.getText().clear();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                String surname1 = surname.getText().toString();

                if (id1.isEmpty() && name1.isEmpty() && surname1.isEmpty()){
                    Toast.makeText(Task2Activity.this, "Please fill data first", Toast.LENGTH_SHORT).show();
                }else{
                    dataBase.UpdateData(id1, name1, surname1);
                    recycle();

                }
                id.getText().clear();
                name.getText().clear();
                surname.getText().clear();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString();

                if (id1.isEmpty()){
                    Toast.makeText(Task2Activity.this, "Please Insert Id", Toast.LENGTH_SHORT).show();
                }
                else{
                    dataBase.DeleteData(id1);
                    recycle();

                }
                id.getText().clear();
            }
        });
    }

    private void recycle() {
        modellist2.clear();
        List<Data2Model> data2Models = dataBase.RetriveData();
        for (int i=0; i<data2Models.size(); i++){
            Data2Model model = new Data2Model();
            model.setId(data2Models.get(i).getId());
            model.setName(data2Models.get(i).getName());
            model.setSurname(data2Models.get(i).getSurname());
            modellist2.add(model);
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(Task2Activity.this,RecyclerView.VERTICAL,false);
        Data2Adapter adapter = new Data2Adapter(Task2Activity.this,modellist2);
        datashow.setLayoutManager(manager);
        datashow.setAdapter(adapter);
    }
}