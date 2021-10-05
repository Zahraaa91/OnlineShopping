package com.example.secondproject;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Camera;
import com.example.secondproject.Recyclers.RecyclerAdapter3;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private ArrayList<Camera> cameraList;
    private RecyclerAdapter3 recyclerAdapter3;
    private Context mContext = CameraActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        cameraList = new ArrayList<>();

        init();


    }

    private void init(){
        clearAll();
        Query query = reference.child("Camera");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Camera camera = new Camera();
                    camera.setUrl(snapshot.child("url").getValue().toString());
                    camera.setName(snapshot.child("name").getValue().toString());
                    camera.setPrice(snapshot.child("price").getValue().toString());
                    cameraList.add(camera);
                }
                recyclerAdapter3 = new RecyclerAdapter3(mContext, cameraList);
                recyclerView.setAdapter(recyclerAdapter3);
                recyclerAdapter3.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void clearAll() {

        if (cameraList != null) {
            cameraList.clear();

            if (recyclerAdapter3 != null) {
                recyclerAdapter3.notifyDataSetChanged();
            }
        }
        cameraList = new ArrayList<>();

    }

}

