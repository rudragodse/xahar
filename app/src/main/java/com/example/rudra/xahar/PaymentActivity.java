package com.example.rudra.xahar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private FirebaseDatabase database;

    TextView username;
    TextView email_address;
    TextView phone_no;
    TextView address;
    TextView country;
    TextView city;
    Button proceed_to_payment;
    String selecteditem;
    Spinner payment;
    String usernames;


    static String [] items= {
            "VIA DEBIT CARD/CREDIT CARD",
            "NET BANKING",
            "PAYMENT BY COD",
            "PAYMENT BY PAYTM"
    };

    public void ChangeActivity(View view)
    {
        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);
    }



    public void Pay(View view) {
        if (selecteditem == items[0]) {
            String debit = payment.getSelectedItem().toString();
            Intent intent = new Intent(getApplicationContext(), OrderPlaced.class);
            intent.putExtra("Payment", debit);
            startActivity(intent);
        } else if (selecteditem == items[1]) {
            String net = payment.getSelectedItem().toString();
            Intent intent = new Intent(getApplicationContext(), OrderPlaced.class);
            intent.putExtra("Payment", net);
            startActivity(intent);
        } else if (selecteditem == items[2]) {
            String cod = payment.getSelectedItem().toString();
            Intent intent = new Intent(getApplicationContext(), OrderPlaced.class);
            intent.putExtra("Payment", cod);
            startActivity(intent);
        } else {
            String paytm = payment.getSelectedItem().toString();
            Intent intent = new Intent(getApplicationContext(), OrderPlaced.class);
            intent.putExtra("Payment", paytm);
            startActivity(intent);
        }
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

            username=(TextView)findViewById(R.id.UsernameText);
            email_address =(TextView)findViewById(R.id.EmailText);
            phone_no=(TextView)findViewById(R.id.PhoneText);
            address=(TextView)findViewById(R.id.AddressText);
            proceed_to_payment=(Button)findViewById(R.id.ProceedText);
            
            usernames=FirebaseAuth.getInstance().getCurrentUser().getEmail();

            payment = (Spinner)findViewById(R.id.payment_options);



            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);

            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            payment.setAdapter(arrayAdapter);
            selecteditem=payment.getSelectedItem().toString();
            Intent intent=getIntent();
            String add=intent.getStringExtra("Address");
            String use=intent.getStringExtra("user");
            String soc=intent.getStringExtra("Society");

            address.setText(use);
            username.setText(usernames);
            phone_no.setText("9766533456");
            email_address.setText(usernames);




        }
}
