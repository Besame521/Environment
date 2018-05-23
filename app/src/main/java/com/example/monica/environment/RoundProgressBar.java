package com.example.monica.environment;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class RoundProgressBar extends View {
    public RoundProgressBar(Context context){this(context,null);}
    public RoundProgressBar(Context context, AttributeSet attrs){this(context,attrs,0);}
    public RoundProgressBar(Context context, AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        //inflate(context,R.layout.round_progress_bar,null);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBar);

    }
}
