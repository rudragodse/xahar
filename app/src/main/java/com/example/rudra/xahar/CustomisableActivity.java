package com.example.rudra.xahar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class CustomisableActivity extends AppCompatActivity {

    public static  CircleMenu menu;

    String[]fooditems={
            "pizzademo",
            "friesdemo",
            "Burger",
            "Hot Dog",
            "Beverages",
            "Waffle"
    };

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Ingrediants");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customisable);

        CircleMenu menu=findViewById(R.id.BurgerCustomise);
        menu.setMainMenu(Color.parseColor("#FFF7F7F7"),R.mipmap.meal_50px,R.mipmap.meals_50px)
        .addSubMenu(Color.parseColor("#FFFFC905"),R.drawable.pizza_icon)
        .addSubMenu(Color.parseColor("#FFFFC905"),R.drawable.fries_icon)
        .addSubMenu(Color.parseColor("#FFFFC905"),R.mipmap.burger_icon)
        .addSubMenu(Color.parseColor("#FFFFC905"),R.drawable.hotdog_icon)
        .addSubMenu(Color.parseColor("#FFFFC905"),R.drawable.drink_icon)
        .addSubMenu(Color.parseColor("#FFFFC905"),R.drawable.waffle_icon)
        .setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {
                Toast.makeText(CustomisableActivity.this, "You selected"+ fooditems[i], Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MenuCustomize.class);
                intent.putExtra("TAG",fooditems[i]);
                //if error delete


                startActivity(intent);
            }
        }).setScaleX((float) 1.2);


    }
}
