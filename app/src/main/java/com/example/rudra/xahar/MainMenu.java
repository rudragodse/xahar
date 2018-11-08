package com.example.rudra.xahar;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainMenu extends AppCompatActivity   {

    public static CircleImageView pizza;
    public static CircleImageView fries;
    public static CircleImageView burger;
    public static CircleImageView HotDog;
    public static CircleImageView drink;
    public static CircleImageView waffles;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public static String name;
    public static String cost;

    Dialog mDialog;
    Button ClassicHamburger;
    MenuInflater inflater;
    LayoutInflater layoutInflater;










    public void CustomiseMenu(View view)
    {
        Intent intent=new Intent(getApplicationContext(),CustomisableActivity.class);
        startActivity(intent);
    }





    public void pizza(View view)
    {
        mDialog.setContentView(R.layout.pizza_popup);
        mDialog.show();
    }
    public void fries(View view)
    {
        mDialog.setContentView(R.layout.friespopup);
        mDialog.show();
    }
    public void burger(View view)
    {
        mDialog.setContentView(R.layout.burgerpopup);
        mDialog.show();
    }
    public void hotDog(View view)
    {
        mDialog.setContentView(R.layout.hotdogpopup);
        mDialog.show();
    }
    public void coke(View view)
    {
        mDialog.setContentView(R.layout.drinkpopup);
        mDialog.show();
    }
    public void waffleOrder(View view)
    {
        mDialog.setContentView(R.layout.wafflepopup);
        mDialog.show();
    }

    public void cartActivity(View view)
    {
        Intent intent=new Intent(getApplicationContext(),CartActivity.class);
        //changed the button name to view
        String Tag=view.getTag().toString();


        intent.putExtra("TAG",Tag);

        if (Tag!=null)
        {
            name=Tag;
            cost="150";
            String id=myRef.push().getKey();
            if (id!=null)
            {
              Cart cart=new Cart(name,cost);
              myRef.child(id).setValue(cart);

            }

        }

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();




        ActionBar actionBar=getSupportActionBar();
        //actionBar.setTitle("Main Menu");

        pizza=(CircleImageView)findViewById(R.id.pizza);
        fries=(CircleImageView)findViewById(R.id.fries);
        burger=(CircleImageView)findViewById(R.id.burger);
        HotDog=(CircleImageView)findViewById(R.id.hotdog);
        drink=(CircleImageView)findViewById(R.id.coke);
        waffles=(CircleImageView)findViewById(R.id.waffle);

         ClassicHamburger=(Button)findViewById(R.id.ClassicHamburger);

        mDialog=new Dialog(this);












    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId()==R.id.log)
       {
           auth.signOut();
           startActivity(new Intent(getApplicationContext(),LogInActivity.class));
       }
        return super.onOptionsItemSelected(item);
    }
    /* Intent intent=new Intent(getApplicationContext(),itemsScrollActivity.class);

        if (view.getTag()==pizza.getTag())
    {
        intent.putExtra("tag",pizza.getTag().toString());
    }
        else if (view.getTag()==fries.getTag())
    {
        intent.putExtra("tag",fries.getTag().toString());
    }
        else if (view.getTag()==burger.getTag())
    {
        intent.putExtra("tag",burger.getTag().toString());
    }
        else if (view.getTag()==HotDog.getTag())
    {
        intent.putExtra("tag",HotDog.getTag().toString());
    }
        else if (view.getTag()==drink.getTag())
    {
        intent.putExtra("tag",drink.getTag().toString());
    }
        else if (view.getTag()==waffles.getTag())
    {
        intent.putExtra("tag",waffles.getTag().toString());
    }

    startActivity(intent);*/
}
