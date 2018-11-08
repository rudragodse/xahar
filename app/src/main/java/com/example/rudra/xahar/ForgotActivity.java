package com.example.rudra.xahar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    Button ForgotButton;
    ProgressDialog progressDialog;

    public void Link(View view)
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Sending the link");
        progressDialog.show();
        Toast.makeText(this, "Link has been sent successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        ForgotButton=(Button)findViewById(R.id.forgotButton);



    }
}
