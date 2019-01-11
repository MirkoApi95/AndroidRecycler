package com.example.mirko.recileview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimaleHolder extends RecyclerView.ViewHolder {

    private TextView testo;
    private ImageView img;

    public AnimaleHolder(@NonNull View itemView) {
        super(itemView);
        testo=itemView.findViewById(R.id.textView);
        img=itemView.findViewById(R.id.imageView);
    }

    public void bind(Animale animal){
    testo.setText(animal.getRazza());
    img.setImageURI(animal.getImg());
    }

}
