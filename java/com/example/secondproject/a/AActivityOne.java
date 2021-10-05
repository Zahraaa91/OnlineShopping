package com.example.secondproject.a;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import com.example.secondproject.MainActivity;

import com.example.secondproject.PassActivity;

import com.example.secondproject.R;
import com.example.secondproject.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AActivityOne extends AppCompatActivity  implements View.OnClickListener{

    public static String  PREFS_NAME="mypre";
    public static String PREF_EMAIL="email";
    public static String PREF_PASSWORD="password";
    private EditText Email;
    private EditText Password;
    private Button btGo;
    private CardView cv;
    private TextView tv;
    private FloatingActionButton fab;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_one);


        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){

            finish();

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        Email = findViewById(R.id.etemail);
        Password =  findViewById(R.id.etpassword);
        btGo = findViewById(R.id.bt_go);
        fab  = findViewById(R.id.fab);
        progressDialog = new ProgressDialog(this);
        btGo.setOnClickListener(this);
        fab.setOnClickListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AActivityOne.this, fab, fab.getTransitionName());
                startActivity(new Intent(AActivityOne.this, AActivityTwo.class), options.toBundle());
            }
        });

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Explode explode = new Explode();
                explode.setDuration(250);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(AActivityOne.this);
                Intent i2 = new Intent(AActivityOne.this,MainActivity.class);
                startActivity(i2, oc2.toBundle());



            }
        });




        tv=findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PassActivity.class));
                finish();
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }


    private void userLogin(){
        String email = Email.getText().toString().trim();
        String password  = Password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Login in Please Wait...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == btGo){
            userLogin();
        }

        if(view == fab){
            finish();
            startActivity(new Intent(this, AActivityTwo.class));
        }
    }
    /////
    public void onStart(){
        super.onStart();
        getUser();
    }

    public void doLogin(View view){
       // EditText txtuser=findViewById(R.id.txt_user);
       // EditText txtpwd=findViewById(R.id.txt_pwd);

        String email="myusername";
        String password="mypassword";

        if(Email.getText().toString().equals(email) && Password.getText().toString().equals(password)){
            CheckBox ch=findViewById(R.id.remember);
            if(ch.isChecked())
                rememberMe(email,password); //save username and password
            //show logout activity
            showLogout(email);

        }
        else{
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }


    }

    public void getUser(){
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String email = pref.getString(PREF_EMAIL, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if (email != null || password != null) {
            //directly show logout form
            showLogout(email);
        }
    }

    public void rememberMe(String email, String password){
        //save username and password in SharedPreferences
        getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                .edit()
                .putString(PREF_EMAIL,email)
                .putString(PREF_PASSWORD,password)
                .commit();
    }

    public void showLogout(String email){
        //display log out activity
        Intent intent=new Intent(this, SplashActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    ////////
}
