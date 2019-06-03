package com.example.pintfinder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ListOffersHolder extends RecyclerView.ViewHolder {
    private TextView pubName;
    private TextView offerName;
    private TextView expireTime;

    public ListOffersHolder(View itemView) {
        super(itemView);
        this.pubName = itemView.findViewById(R.id.pubName);
        this.offerName = itemView.findViewById(R.id.offerName);
        this.expireTime = itemView.findViewById(R.id.expireTime);
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

    public void setDetails(Offer offer) {
        pubName.setText(offer.getPubName());
        offerName.setText(offer.getName());
        expireTime.setText(offer.getExpireTime());
    }
}