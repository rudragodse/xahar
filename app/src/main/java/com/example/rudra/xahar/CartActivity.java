package com.example.rudra.xahar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity  {

    Button checkout;
    ArrayList<String> items;
    TextView textView;
    String keyresult;
    String value;
    String key;
    static HashMap<String, String> itemCost = new HashMap<String, String>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cart");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        items = new ArrayList<>();
        listView = findViewById(R.id.cart_items);
        //arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                items.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Cart cart = postsnapshot.getValue(Cart.class);
                    Log.i("Object Data", String.valueOf(cart));
                    items.add(cart.getItem_name() + " - Rs." + cart.getItem_cost());

                }
                arrayAdapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_list_item_1, items);
                listView.setAdapter(arrayAdapter);

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                        new AlertDialog.Builder(CartActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("are you sure you want this")
                                .setMessage("Do you definetly want to do this").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "It's done", Toast.LENGTH_SHORT).show();
                            }
                        })
                                .setNegativeButton("no",null)
                                .show();

                        return false;
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void Payment(View view)
    {
        Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
        startActivity(intent);
    }

    }

/*
        ActionBar actionBar=getSupportActionBar();
        try
        {
            actionBar.hide();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        itemsList=findViewById(R.id.CardItems);

        final ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);

        itemsList.setAdapter(arrayAdapter);

        checkout=findViewById(R.id.checkout);

        //items=new ArrayList<>();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference().child("Rudy");
//        Log.i("Hello","World");
//        Log.i("My ref val: ",myRef.toString());
//        Log.i("Val retrives",myRef.child("01").toString());
//        Log.i("Val retrives","Hua");



//changed refrence
//        myRef.child("05").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String value=dataSnapshot.getValue(String.class);
//                items.add(value);
//                Log.i("Aandar","aya");
//                Log.i("Value",value.toString());
//;                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        //commented the hashmap

      final String[]itemsNames={
               "Classic Hamburger",
               "Bacon Whopper Burger",
               "Bacon Cheese Burger",
               "Jalepino Burger"
       };
      final String[]pizzaNames={
              "ClassicMargherita",
              "Mexican Green Wave",
              "Double Cheese Margherita",
              "Farmhouse"
      };

        Intent intent=getIntent();

        final String id =intent.getStringExtra("TAG");
        Log.i("Value:id",id);


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //commented the key;Change the key again, May give an null exception
                  key = dataSnapshot.getKey().toString();
                 Log.i("Key",key);
                 //ADDED THIS NEW LINE AT132

                 value=dataSnapshot.getValue().toString();
                Log.i("Valueis:",value);
               itemCost.put(key,value);
               //I AM ADDING itemCost TO THE LIST, CHECK IF ERROR IF TO STRING IS GIVING THE ERROR
                //items.add(itemCost.toString());
                //arrayAdapter.notifyDataSetChanged();
               //ADDED THE VALUE NOW TO CHECK WHETEER IT PRINTS
                //ERRORS MIGHT BE IN LISTVIEW
                //MOVING THE ITEMS.ADD FUNCTION OUTSIDE THE FOR LOOP
                //CHECK FOR ERRORS ON LINES 149,150
                //ITS ADDING THE ITEMS EVERYTIME THE CHILD IS ADDED,SO MOVING OUTSIDE THIS METHOD



                //comented the itemcost
                //itemCost.put(key,value);
                //changed the functiuon to contains value and changed the datatypes

                 keyresult=key;
                 Integer length=keyresult.length();
                 Log.i("Lengthresult:",length.toString());

                 Log.i("keyresult",keyresult);



//                Log.i("Rudy_key",key);
//                Log.i("Rudy",value);
               //items.add(value);
               // arrayAdapter.notifyDataSetChanged();

            }





            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //ADDING THE VALUE EVENT LISTNER OVER HERE TO GET THE VALUES
        //TRY CHECKING FOR ERRORS
        //ADDED THE ENTIRE VAL TO LIST
        String[]BurgerCost={
                "150",
                "150",
                "150",
                "150"
        };
        String []PizzaCost={
                "300"
        };
        //ADDED THE PIZZA NAMES ARRAY
//        Log.i("Length",String.valueOf(itemCost.size()));
//        for(Map.Entry<String, String> entry : itemCost.entrySet())
//        {
//            Log.i("mathya","math");
//            Log.i(entry.getKey(),entry.getValue());
//        }
//        if(itemCost.containsKey("maggi"))
//        {
//            Log.i("Hala","Hola");
//        }
//        else
//        {
//            Log.i("Hola","Hala");
//        }
        for (int i=0;i<itemsNames.length;i++)
        {
            if (id.equalsIgnoreCase(itemsNames[i]))
            {
                Log.i("YAY","Value found");
                items.add(id+" "+BurgerCost[i]);
               // arrayAdapter.notifyDataSetChanged();
            }
        }









       //added a string array to check its presence, As Hashmap was causing errors
        //POSSIBLE ERRORS AT LINE 193
        //POSSIBLE ERRORS AT LINE 195,194
        //MOVED THE FOR LOOP INSIDE THE ONCHILDADDED()METHOD
        //MOVED THE INTENT BEFORE THE ONCHILDADDED




      /* if (itemCost.get("Classic Hamburger").equalsIgnoreCase(id))
       {
           Log.i("Congrats","value present");
       }*/

//Problem occurs at Boolean ValStats, Okay, As it returns false everytime everthough key is present.
// Boolean ValStats=itemCost.containsKey("Classic Hamburger");
//Log.i("ValStats",ValStats.toString());


//Added Value Event Listner
        /*
         String itemtag= intent.getStringExtra("Tag");
               String ItemsSelected=intent.getStringExtra("Items");
         */

//String val=itemCost.get("Classic Hamburger");
//Log.i("Hashresult:",val);






