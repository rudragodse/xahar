package com.example.rudra.xahar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mauth;
    EditText userEditText;
    EditText passEditText;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private TextView backtraversal;

    public void Back(View view)
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void SlideMenu(View view)
    {

        String username = userEditText.getText().toString().trim();
        String password = passEditText.getText().toString().trim();


        if (TextUtils.isEmpty(username)) {

            Toast.makeText(this, "email address is too short", Toast.LENGTH_SHORT).show();
            return;

        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Loggin In...");
        progressDialog.show();

        auth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LogInActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),SliderActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LogInActivity.this, "Couldnot log in,check username or password", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });




    }

    public void ForgotActivity(View view)
    {
        Intent intent=new Intent(getApplicationContext(),ForgotActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        auth=FirebaseAuth.getInstance();

        backtraversal=findViewById(R.id.backtraversaltext);

        progressDialog=new ProgressDialog(this);

        if (auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),SliderActivity.class));
        }


        userEditText=(EditText)findViewById(R.id.userEditText);
        passEditText=(EditText)findViewById(R.id.passEditText);


    }
}

/* mauth= new FirebaseAuth.AuthStateListener() {
             @Override
             public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                 if (firebaseAuth.getCurrentUser()==null)
                 {
                     Toast.makeText(LogInActivity.this, "Log In successful"+ "Welcome " +firebaseAuth.getCurrentUser().getEmail().toString(), Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     Toast.makeText(LogInActivity.this, "User" + firebaseAuth.getCurrentUser().getEmail()+ "is logged in", Toast.LENGTH_SHORT).show();
                 }

             }
         };
        if (userEditText.getText()==null && passEditText.getText()==null)
        {
            Toast.makeText(this, "Please enter username or Password", Toast.LENGTH_SHORT).show();
        }

        Intent intent=new Intent(getApplicationContext(),SliderActivity.class);
        intent.putExtra("mauth",mauth.toString());
        startActivity(intent);
*/