package com.example.roomdbapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

public class AddNewContactClickHandler  {

    Contact contact;
    MyViewModel myViewModel;
    Context context;

    public AddNewContactClickHandler(Context context, Contact contact, MyViewModel myViewModel) {
        this.context = context;
        this.contact = contact;
        this.myViewModel = myViewModel;
    }

    public void Clicked(View view){
        if (contact.getName() == null || contact.getEmail() == null){
            Toast.makeText(context,"Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("name" , contact.getName());
//            i.putExtra("email",contact.getEmail());

            Contact c = new Contact(
                    contact.getName(),
                    contact.getEmail()
            );

            myViewModel.addNewContact(c);

            context.startActivity(i);
        }
    }


}
