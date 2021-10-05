package com.example.secondproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Apple;
import com.example.secondproject.Recyclers.RecyclerAdapter1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class AppleActivity extends AppCompatActivity {


    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private ArrayList<Apple> appleList;
    private RecyclerAdapter1 recyclerAdapter1;
    private Context mContext = AppleActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);


        recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        appleList = new ArrayList<>();
        init();



    }

    private void init(){
        clearAll();
       Query query = reference.child("Apple");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Apple apple = new Apple();
                    apple.setUrl(snapshot.child("url").getValue().toString());
                    apple.setName(snapshot.child("name").getValue().toString());
                    apple.setPrice(snapshot.child("price").getValue().toString());
                    appleList.add(apple);
                }
                recyclerAdapter1 = new RecyclerAdapter1(mContext, appleList);
                recyclerView.setAdapter(recyclerAdapter1);
                recyclerAdapter1.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    private void clearAll() {

        if (appleList != null) {
            appleList.clear();

            if (recyclerAdapter1 != null) {
                recyclerAdapter1.notifyDataSetChanged();
            }
        }
        appleList = new ArrayList<>();

    }


}

