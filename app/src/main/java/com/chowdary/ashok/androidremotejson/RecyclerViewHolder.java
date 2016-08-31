package com.chowdary.ashok.androidremotejson;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView contentTitle;

    public ImageView contentImage;

    public TextView contentCategory;

    public TextView contentDescription;


    public RecyclerViewHolder(View itemView) {
        super(itemView);

        contentTitle = (TextView)itemView.findViewById(R.id.content_title);
        contentImage = (ImageView)itemView.findViewById(R.id.content_image);
        contentCategory = (TextView)itemView.findViewById(R.id.content_category);
        contentDescription = (TextView)itemView.findViewById(R.id.content_description);
    }
}
