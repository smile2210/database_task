package com.example.mytaskdatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaskdatabase.Database.Task2DataBase;

import java.util.List;

public class Data2Adapter extends RecyclerView.Adapter<Data2Adapter.Data2Holder> {

    Activity activity;
    List<Data2Model> data2ModelList;
    Data2Model model;

    public Data2Adapter(Task2Activity task2Activity, List<Data2Model> data2ModelList) {
        activity = task2Activity;
        this.data2ModelList = data2ModelList;
    }

    @NonNull
    @Override
    public Data2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dataview,parent,false);
        return new Data2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Data2Holder holder, final int position) {
        holder.idview.setText(data2ModelList.get(position).getId());
        holder.nameview.setText(data2ModelList.get(position).getName());
        holder.surnameview.setText(data2ModelList.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return data2ModelList.size();
    }


    public class Data2Holder extends RecyclerView.ViewHolder {
        TextView idview,nameview,surnameview;

        public Data2Holder(@NonNull View itemView) {
            super(itemView);

            idview = itemView.findViewById(R.id.idview);
            nameview = itemView.findViewById(R.id.nameview);
            surnameview = itemView.findViewById(R.id.surnameview);
        }
    }
}
