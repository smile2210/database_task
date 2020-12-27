package com.example.mytaskdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mytaskdatabase.Database.Task1DataBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Task1Activity extends AppCompatActivity {

    EditText id, name, surname;
    Button back, insert, update, delete, retrive;
    ListView datashow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        back = findViewById(R.id.back);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        retrive = findViewById(R.id.retrive);
        datashow = findViewById(R.id.datashow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Task1DataBase dataBase = new Task1DataBase(Task1Activity.this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                String surname1 = surname.getText().toString();

                if (id1.isEmpty() && name1.isEmpty() && surname1.isEmpty()){
                    Toast.makeText(Task1Activity.this, "Please Data insert", Toast.LENGTH_SHORT).show();
                }
                else{
                    dataBase.Insertdata(id1, name1, surname1);
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
                    Toast.makeText(Task1Activity.this, "Please fill the data", Toast.LENGTH_SHORT).show();
                }
                else{
                    dataBase.UpdateData(id1, name1, surname1);
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
                    Toast.makeText(Task1Activity.this, "Please fill id first", Toast.LENGTH_SHORT).show();
                }
                else{
                    dataBase.DeleteData(id1);
                }
                id.getText().clear();
                name.getText().clear();
                surname.getText().clear();
            }
        });

        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<DataModel> dataModel = dataBase.RetriveData();
                List<DataModel> modelList = new ArrayList<>();

                for (int i=0; i<dataModel.size(); i++) {
                    DataModel model1 = new DataModel();
                    model1.setId(dataModel.get(i).getId());
                    model1.setName(dataModel.get(i).getName());
                    model1.setSurname(dataModel.get(i).getSurname());
                    modelList.add(model1);
                }
                    DataAdapter adapter = new DataAdapter(Task1Activity.this,modelList);
                    datashow.setAdapter(adapter);
            }
        });


    }
}