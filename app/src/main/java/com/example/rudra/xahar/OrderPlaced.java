package com.example.rudra.xahar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class OrderPlaced extends AppCompatActivity {

    String payMethod;
    TextView paymentinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);


        Intent intent=getIntent();
        Log.i("payment",intent.getStringExtra("Payment"));
        payMethod=intent.getStringExtra("Payment");


    }
}
