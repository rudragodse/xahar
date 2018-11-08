package com.example.rudra.xahar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SliderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private SliderAdapter sliderAdapter;

    private TextView[]mdots;

    TextView texuser;


    public void MainMenu(View view)
    {
        Intent intent=new Intent(getApplicationContext(),MainMenu.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        texuser=(TextView)findViewById(R.id.textuser);

        Intent intent=getIntent();
        intent.getStringExtra("mauth");

        FirebaseAuth.AuthStateListener mauthe=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                texuser.setText(firebaseAuth.getCurrentUser().getEmail());

                Log.i("User:",firebaseAuth.getCurrentUser().getEmail());

            }
        };






        viewPager=(ViewPager)findViewById(R.id.viewPager);
        linearLayout=(LinearLayout)findViewById(R.id.linear);

        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        mdots=new TextView[3];
        for (int i=0;i<mdots.length;i++)
        {
            mdots[i]=new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            linearLayout.addView(mdots[i]);

        }
    }
}
