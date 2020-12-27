package com.example.mytaskdatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends BaseAdapter {
    Activity activity;
    List<DataModel>modelList;

    public DataAdapter(Task1Activity task1Activity, List<DataModel> modelList) {
        activity = task1Activity;
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(activity).inflate(R.layout.dataview,viewGroup,false);
        TextView idview = view.findViewById(R.id.idview);
        TextView nameview = view.findViewById(R.id.nameview);
        TextView surnameview = view.findViewById(R.id.surnameview);

        idview.setText(modelList.get(i).getId());
        nameview.setText(modelList.get(i).getName());
        surnameview.setText(modelList.get(i).getSurname());
        return view;
    }
}
