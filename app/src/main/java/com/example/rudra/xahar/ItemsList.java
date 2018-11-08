package com.example.rudra.xahar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemsList extends ArrayAdapter<Cart> {

    private  ArrayList<Cart> items=new ArrayList<>();

    public ItemsList(Activity context, ArrayList<Cart> items) {
        super(context, R.layout.activity_cart, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_cart, null, true);

        Cart cart= items.get(position);


        return listViewItem;
    }

    private Activity context;

}
