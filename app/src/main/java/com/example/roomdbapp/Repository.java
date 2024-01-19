package com.example.roomdbapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Repository {

    public ExecutorService executorService;
    public Handler handler;

    private final ContactDAO contactDAO;


    public Repository(Application application) {
        ContactDataBase contactDataBase = ContactDataBase.getInstance(application);
        this.contactDAO = contactDataBase.getContactDAO();
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }



    public void addContact(Contact contact){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });

    }
    public void deleteContact(Contact contact){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });

    }

    public LiveData<List<Contact>> getAllContact(){
        return contactDAO.getAllContacts();

    }
}
