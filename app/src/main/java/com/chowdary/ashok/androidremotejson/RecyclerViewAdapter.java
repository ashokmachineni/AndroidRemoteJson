package com.chowdary.ashok.androidremotejson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private Context context;

    private List<JsonObjectWrapper> jsonDataObject;

    public RecyclerViewAdapter(Context context, List<JsonObjectWrapper> jsonDataObject){
        this.context = context;
        this.jsonDataObject = jsonDataObject;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.json_list_layout, parent, false);
        RecyclerViewHolder jsonHolder = new RecyclerViewHolder(layoutView);
        return jsonHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.contentTitle.setText(jsonDataObject.get(position).getTitle());
        holder.contentCategory.setText("Posted under " + jsonDataObject.get(position).getCategory() + " category");
        holder.contentDescription.setText(jsonDataObject.get(position).getDescription());
        Picasso.with(context.getApplicationContext()).load(jsonDataObject.get(position).getPoster()).into(holder.contentImage);
    }

    @Override
    public int getItemCount() {
        return jsonDataObject.size();
    }
}
