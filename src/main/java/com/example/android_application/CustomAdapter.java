package com.example.android_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList country_id, country_title, country_capital, country_number;

    CustomAdapter(Activity activity, Context context, ArrayList country_id, ArrayList country_title, ArrayList country_capital,
                  ArrayList country_number){
        this.activity = activity;
        this.context = context;
        this.country_id = country_id;
        this.country_title = country_title;
        this.country_capital = country_capital;
        this.country_number = country_number;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.country_id_txt.setText(String.valueOf(country_id.get(position)));
        holder.country_title_txt.setText(String.valueOf(country_title.get(position)));
        holder.country_capital_txt.setText(String.valueOf(country_capital.get(position)));
        holder.country_number_txt.setText(String.valueOf(country_number.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(country_id.get(position)));
                intent.putExtra("title", String.valueOf(country_title.get(position)));
                intent.putExtra("author", String.valueOf(country_capital.get(position)));
                intent.putExtra("pages", String.valueOf(country_number.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return country_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView country_id_txt, country_title_txt, country_capital_txt, country_number_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            country_id_txt = itemView.findViewById(R.id.country_id_txt);
            country_title_txt = itemView.findViewById(R.id.country_title_txt);
            country_capital_txt = itemView.findViewById(R.id.country_capital_txt);
            country_number_txt = itemView.findViewById(R.id.country_number_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
