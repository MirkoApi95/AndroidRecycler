package com.example.mirko.recileview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimaleHolder extends RecyclerView.ViewHolder {

    private TextView testo;
    private ImageView img;
    int x=0;
    private AnimaleAdapter a;

    public AnimaleHolder(@NonNull View itemView) {
        super(itemView);
        testo=itemView.findViewById(R.id.textView);
        img=itemView.findViewById(R.id.imageView);

        testo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test","Name clicked : "+getAdapterPosition());
                x=getAdapterPosition();
                delete();
            }
        });
    }
    private void delete() { //removes the row
        a=new AnimaleAdapter();
        a.animali.remove(x);
        a.deletee(x);
    }

    public void bind(Animale animal){
    testo.setText(animal.getRazza());
    img.setImageURI(animal.getImg());
    }
}
