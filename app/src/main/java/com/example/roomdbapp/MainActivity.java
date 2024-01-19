package com.example.roomdbapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.roomdbapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContactDataBase contactDataBase;

    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    private MyAdapter myAdapter;

    private ActivityMainBinding mainBinding;

    private MainActivityClickHandler clickHandler;

    MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new MainActivityClickHandler(this);

        mainBinding.setClickHandler(clickHandler);

        RecyclerView recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);




        contactDataBase = ContactDataBase.getInstance(this);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        viewModel.getAllContact().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                contactArrayList.clear();

                for (Contact c : contacts){
                    contactArrayList.add(c);
                }

                myAdapter.notifyDataSetChanged();

            }
        });

        myAdapter = new MyAdapter(contactArrayList);

        recyclerView.setAdapter(myAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact c = contactArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(c);

            }
        }).attachToRecyclerView(recyclerView);


    }
}