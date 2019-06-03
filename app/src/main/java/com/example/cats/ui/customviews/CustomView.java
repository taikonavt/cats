package com.example.cats.ui.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cats.R;

public class CustomView extends ConstraintLayout{

    View rootView;
    ImageView imageView;
    TextView textView;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.item_cat, this);
        textView = (TextView) rootView.findViewById(R.id.item_cat_name_tv);
        imageView = (ImageView) rootView.findViewById(R.id.item_cat_image);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setImageBitmap(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    public void setImageResource(int resId){
        imageView.setImageResource(resId);
    }
}
