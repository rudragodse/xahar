package com.example.rudra.xahar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
     public LayoutInflater layoutInflater;
     Button orderNow;







    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    public int[] slide_images={
            R.drawable.medal,
            R.drawable.junk,
            R.drawable.delivery
    };

    public String []slide_headings={
            "Eat from the best Restaurant",
            "Choose the fast food you love",
            "Sit back, food is on it's way"
    };

    public String []slide_desc={
            "Prepare to be enlightened by the mindboggling flavours",
            "Our delivery service offers a wide range of fresh meals prepared at the moment",
            "Get ready and comfortable while our bikers bring the food at your doorstep"
    };



    @Override
    public int getCount() {
        return slide_images.length;



    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
     View view=   layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView=view.findViewById(R.id.slideImage);
        TextView slideTextHead=view.findViewById(R.id.header);
        TextView slideTextDesc=view.findViewById(R.id.slideTextDesc);
         orderNow=(Button)view.findViewById(R.id.orderNow);

        slideImageView.setImageResource(slide_images[position]);
        slideTextHead.setText(slide_headings[position]);
        slideTextDesc.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
