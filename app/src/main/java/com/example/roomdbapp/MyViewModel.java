package com.example.roomdbapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Contact>> contact;
    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);

    }

    public LiveData<List<Contact>> getAllContact(){
        contact = repository.getAllContact();
        return contact;
    }

    public void addNewContact(Contact contact){
        repository.addContact(contact);

    }

    public void deleteContact(Contact contact){
        repository.deleteContact(contact);
    }
}
