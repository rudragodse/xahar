package com.example.rudra.xahar;

import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {


    // <supports-screens>
    //        android:resizeable="true"
    //        android:smallScreens="true",
    //        android:normalScreens="true"
    //        android:largeScreens="true"
    //        android:xlargeScreens="true"
    //        android:anyDensity="true"
    //    </supports-screens>
    // Changes made in database ref in emailid and passwordid;
    //object to be passed changed from user to mainActivity;


    EditText emailEditText;
    EditText passwordEditText;
    Button signInButton;
    TextView orTextView;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    //  DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();


    EditText NameEditText;
    EditText Mob;

    public void LogIn(View view) {

        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(intent);
    }


    public void Register(View view) {
         String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {

            Toast.makeText(this, "email address is too short", Toast.LENGTH_SHORT).show();
            return;

        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                   progressDialog.dismiss();
                   startActivity(new Intent(getApplicationContext(),SliderActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NameEditText=findViewById(R.id.nameEditText);
        Mob=findViewById(R.id.Mob);

        


        firebaseAuth=FirebaseAuth.getInstance();

        emailEditText=(EditText)findViewById(R.id.EmailEditText);

        passwordEditText=(EditText)findViewById(R.id.passwordEditText);

        signInButton =(Button)findViewById(R.id.signinButton);

        orTextView=(TextView)findViewById(R.id.ortextView);

         user=firebaseAuth.getCurrentUser();

        if (user!=null)
        {
            Log.i("user:",user.getEmail());
            startActivity(new Intent(getApplicationContext(),SliderActivity.class));
            //databaseReference.child("User:").setValue(user);
        }










    }
}
