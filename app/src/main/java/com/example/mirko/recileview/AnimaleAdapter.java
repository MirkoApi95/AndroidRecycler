package com.example.mirko.recileview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AnimaleAdapter extends RecyclerView.Adapter<AnimaleHolder>{

    private ArrayList<Animale> animali;

    public AnimaleAdapter(ArrayList<Animale> animali) {
        this.animali = animali;
    }

    @NonNull
    @Override
    public AnimaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new AnimaleHolder(view);
    }

    public ArrayList<Animale> getAnimali() {
        return animali;
    }

    public void setAnimali(ArrayList<Animale> animali) {
        this.animali = animali;
    }

    @Override

    public void onBindViewHolder(@NonNull AnimaleHolder holder, int position) {
        Animale animal=animali.get(position);
        holder.bind(animal);
    }


    @Override
    public int getItemCount() {
        return animali.size();
    }
}
