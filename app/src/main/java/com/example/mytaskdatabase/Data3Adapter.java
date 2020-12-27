package com.example.mytaskdatabase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaskdatabase.Database.Task3DataBase;
import java.util.List;

public class Data3Adapter extends RecyclerView.Adapter<Data3Adapter.Data3Holder> {

    Activity activity;
    List<Data3Model> modelList3;
    Task3DataBase dataBase;

    public Data3Adapter(Task3Activity task3Activity, List<Data3Model> modellist3, Task3DataBase dataBase) {
        activity = task3Activity;
        this.modelList3 = modellist3;
        this.dataBase = dataBase;
    }

    @NonNull
    @Override
    public Data3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.task3view,parent,false);
        return new Data3Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Data3Holder holder, final int position) {

        holder.id3.setText(modelList3.get(position).getId());
        holder.name3.setText(modelList3.get(position).getName());
        holder.surename3.setText(modelList3.get(position).getSurname());

        holder.update3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.updatedialog);
                final EditText nameup = dialog.findViewById(R.id.nameup);
                final EditText surnameup = dialog.findViewById(R.id.surnameup);
                Button done = dialog.findViewById(R.id.done);
                Button cancel = dialog.findViewById(R.id.cancel);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataBase.UpdateData(modelList3.get(position).id,nameup.getText().toString(),surnameup.getText().toString());
                        ((Task3Activity)activity).recycle();
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        holder.delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase.DeleteData(modelList3.get(position).id);
                ((Task3Activity)activity).recycle();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList3.size();
    }

    public class Data3Holder extends RecyclerView.ViewHolder {
        TextView id3,name3, surename3;
        Button update3, delete3;
        public Data3Holder(@NonNull final View itemView) {
            super(itemView);

            id3 = itemView.findViewById(R.id.id3);
            name3 = itemView.findViewById(R.id.name3);
            surename3 = itemView.findViewById(R.id.surname3);
            update3 = itemView.findViewById(R.id.update3);
            delete3 = itemView.findViewById(R.id.delete3);
        }
    }
}
