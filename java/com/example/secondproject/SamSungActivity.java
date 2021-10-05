package com.example.secondproject;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Samsung;
import com.example.secondproject.Recyclers.RecyclerAdapter2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class SamSungActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private ArrayList<Samsung> samsungList;
    private RecyclerAdapter2 recyclerAdapter2;
    private Context mContext = SamSungActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samsung);


        recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        samsungList = new ArrayList<>();

        init();

    }

    private void init(){
        clearAll();
        Query query = reference.child("Samsung");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Samsung samsung = new Samsung();
                    samsung.setUrl(snapshot.child("url").getValue().toString());
                    samsung.setName(snapshot.child("name").getValue().toString());
                    samsung.setPrice(snapshot.child("price").getValue().toString());
                    samsungList.add(samsung);
                }
                recyclerAdapter2 = new RecyclerAdapter2(mContext, samsungList);
                recyclerView.setAdapter(recyclerAdapter2);
                recyclerAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void clearAll() {

        if (samsungList != null) {
            samsungList.clear();

            if (recyclerAdapter2 != null) {
                recyclerAdapter2.notifyDataSetChanged();
            }
        }
        samsungList = new ArrayList<>();

    }
}
