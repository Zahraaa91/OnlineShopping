package com.example.secondproject;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Accessories;
import com.example.secondproject.Recyclers.RecyclerAdapter4;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class AccessoriesActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private ArrayList<Accessories> accessoriesList;
    private RecyclerAdapter4 recyclerAdapter4;
    private Context mContext = AccessoriesActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);


        recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        accessoriesList = new ArrayList<>();

        init();

    }

    private void init(){
        clearAll();
        Query query = reference.child("Accessories");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Accessories accessories = new Accessories();
                    accessories.setUrl(snapshot.child("url").getValue().toString());
                    accessories.setName(snapshot.child("name").getValue().toString());
                    accessories.setPrice(snapshot.child("price").getValue().toString());
                    accessoriesList.add(accessories);
                }
                recyclerAdapter4 = new RecyclerAdapter4(mContext, accessoriesList);
                recyclerView.setAdapter(recyclerAdapter4);
                recyclerAdapter4.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void clearAll() {

        if (accessoriesList != null) {
            accessoriesList.clear();

            if (recyclerAdapter4 != null) {
                recyclerAdapter4.notifyDataSetChanged();
            }
        }
        accessoriesList = new ArrayList<>();

    }
}
