package com.example.rudra.xahar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuCustomize extends AppCompatActivity {

    TextView ItemsNames;
    String[]ingrediants={
            "Extra Patty",
            "Sauses",
            "Lettuce",
            "Tomatoes"
    };
   static String itemTag;
   CircleImageView ItemsImage;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_customize);
        ItemsNames=findViewById(R.id.ItemsNames);
        ItemsImage=findViewById(R.id.ItemImage);

        final Intent intent=getIntent();
        String Text=intent.getStringExtra("TAG");
        ItemsNames.setText(Text);
        if (Text.equalsIgnoreCase("Burger"))
        {
            ItemsImage.setImageResource(R.drawable.burgerdemo);
        }
        else if (Text.equalsIgnoreCase("Pizza"))
        {
            ItemsImage.setImageResource(R.drawable.pizzademo);
        }
        else if (Text.equalsIgnoreCase("Fries"))
        {
            ItemsImage.setImageResource(R.drawable.friesdemo);
        }
        else if (Text.equalsIgnoreCase("beverage"))
        {
            ItemsImage.setImageResource(R.drawable.beverage);
        }
        else if (Text.equalsIgnoreCase("Waffle"))
        {
            ItemsImage.setImageResource(R.drawable.waffles);
        }

        final CircleMenu menu=findViewById(R.id.burgeringrediants);
        menu.setMainMenu(Color.parseColor("#FFFDE7EF"),R.mipmap.meals_50px,R.mipmap.meal_50px)
                .addSubMenu(Color.parseColor("#FFFDE7EF"),R.drawable.patties)
                .addSubMenu(Color.parseColor("#FFFDE7EF"),R.drawable.lettuce)
                .addSubMenu(Color.parseColor("#FFFDE7EF"),R.drawable.sauces)
                .addSubMenu(Color.parseColor("#FFFDE7EF"),R.drawable.tomatoes)
                .addSubMenu(Color.parseColor("#FFFDE7EF"),R.drawable.arrow)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {

                        Toast.makeText(MenuCustomize.this, "Selected"+ ingrediants[i], Toast.LENGTH_SHORT).show();
                       //moved the intent in on menuselectedlistener

                         //itemTag=String.valueOf(i);

                        //shifted ItemTag

                    }
                }).setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {

                itemTag=String.valueOf(i);
                Log.i("itemTag:",itemTag);
                Log.i("Items:",ingrediants[i]);
                //pushing has failed:

                Intent intent1=new Intent(getApplicationContext(),CartActivity.class);
                intent1.putExtra("Items",ingrediants[i]);
                intent1.putExtra("itemTag:",itemTag);
                startActivity(intent1);

            }
        });


    }
}
