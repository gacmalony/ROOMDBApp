package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.roomdbapp.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding activityAddNewContactBinding;
    private AddNewContactClickHandler addNewContactClickHandler;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        contact = new Contact();
        activityAddNewContactBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_new_contact
        );

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);



        addNewContactClickHandler = new AddNewContactClickHandler(
                this,
                contact,
                myViewModel

        );
        activityAddNewContactBinding.setContact(contact);
        activityAddNewContactBinding.setClick(addNewContactClickHandler);
    }
}