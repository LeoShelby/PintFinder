package com.example.pintfinder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class ListBeerHolder extends RecyclerView.ViewHolder {
    private TextView name, price; // ricordare che price verrà usato per inserire anche l'address del pub nella schermata del PubOwner
    private ImageView image;
    private ImageView thumb;

    public ListBeerHolder(View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        image = itemView.findViewById(R.id.image);
        price = itemView.findViewById(R.id.price);
        thumb = itemView.findViewById(R.id.thumbImage);
    }


    public Activity getActivity() {
        Context context = itemView.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    public void setDetails(Beer beer) {
        if (getActivity() != null)  {
            name.setText(beer.getName());
            image.setImageResource(beer.getImage());
            if (getActivity() instanceof MenuPubActivity)   {
                Log.e("MenuPubActivity", "Siamo in MenuPubActivity");
                price.setText(beer.getPrice());
            }
            if (getActivity() instanceof ListTastedBeersActivity)   {
                Log.e("ListTastedBeersActivity", "Siamo in ListTastedBeersActivity");
                thumb.setImageResource(beer.getThumb());
                price.setVisibility(View.GONE);
            }
            if (getActivity() instanceof UpdateMenuActivity)   {
                Log.e("ListTastedBeersActivity", "Siamo in ListTastedBeersActivity");
                price.setText(beer.getPrice());
            }

        }
        else    {
            System.out.println("SIAMO DENTRO LISTBEERHOLDER.JAVA");
        }

    }
}
