package com.example.pintfinder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class ListBeerHolder extends RecyclerView.ViewHolder {
    private TextView name, price; // ricordare che price verrà usato per inserire anche l'address del pub nella schermata del PubOwner
    private ImageView image;
    private ImageView thumb;
    private Button check;

    public ListBeerHolder(View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        image = itemView.findViewById(R.id.image);
        price = itemView.findViewById(R.id.price);
        thumb = itemView.findViewById(R.id.thumbImage);
        check = itemView.findViewById(R.id.check);
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
        final Beer birrina = beer;
        if (getActivity() != null)  {
            name.setText(beer.getName());
            image.setImageResource(beer.getImage());
            if (getActivity() instanceof MenuPubActivity)   {
                Log.e("MenuPubActivity", "Siamo in MenuPubActivity");
                price.setText(beer.getPrice());
                check.setVisibility(View.GONE);
                if(SingletonUsers.Instance().getTastedBeers().contains(beer)) {
                    check.setVisibility(View.VISIBLE);
                    check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), AddBeerActivity.class);
                            intent.putExtra("beerName", birrina.getName());
                            intent.putExtra("activity", "ListTastedBeersActivity");
                            intent.putExtra("help","tasted");
                            getActivity().startActivity(intent);
                        }
                    });
                }

            }
            if (getActivity() instanceof ListTastedBeersActivity)   {
                Log.e("ListTastedBeersActivity", "Siamo in ListTastedBeersActivity");
                thumb.setImageResource(beer.getThumb());
                price.setVisibility(View.GONE);
                check.setVisibility(View.GONE);

            }
            if (getActivity() instanceof UpdateMenuActivity)   {
                Log.e("ListTastedBeersActivity", "Siamo in ListTastedBeersActivity");
                price.setText(beer.getPrice());
                check.setVisibility(View.GONE);
            }

        }
        else    {
            System.out.println("SIAMO DENTRO LISTBEERHOLDER.JAVA");
        }

    }
}
