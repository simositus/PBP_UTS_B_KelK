package com.tubes.sandangin;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Tshirt> tshirts;
    private TshirtViewAdapter adapter;
    Tshirt tees;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Tshirt> ListTshirt;

        //get data tshirt
        tshirts = new TshirtList().tshirts;

        //recycler view
        gridView = findViewById(R.id.gridview);
        adapter = new TshirtViewAdapter(ListActivity.this, tshirts);
        gridView.setAdapter(adapter);
    }
}