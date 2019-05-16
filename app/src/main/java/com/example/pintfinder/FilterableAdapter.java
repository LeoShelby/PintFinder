package com.example.pintfinder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    public class FilterableAdapter extends RecyclerView.Adapter<FilterableAdapter.ViewHolder> implements Filterable {

        private Context context;
        private ArrayList<Beer> mArrayList;
        private ArrayList<Beer> mFilteredList;

        public FilterableAdapter(Context context, ArrayList<Beer> arrayList) {
            this.context = context;
            mArrayList = arrayList;
            mFilteredList = arrayList;
        }

        @Override
        public FilterableAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_beer, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FilterableAdapter.ViewHolder viewHolder, int i) {
            final Beer beer = mFilteredList.get(i);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AddBeerActivity.class);
                    intent.putExtra("beerName", beer.getName());
                    intent.putExtra("activity", "SearchBeerFromDatabase");
                    context.startActivity(intent);
                }
            });

            viewHolder.image.setImageResource(mFilteredList.get(i).getImage());
            viewHolder.name.setText(mFilteredList.get(i).getName());
            viewHolder.price.setText(mFilteredList.get(i).getType());
        }

        @Override
        public int getItemCount() {
            return mFilteredList.size();
        }

        @Override
        public Filter getFilter() {

            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    String charString = charSequence.toString();

                    if (charString.isEmpty()) {
                        mFilteredList = mArrayList;
                    } else {

                        ArrayList<Beer> filteredList = new ArrayList<>();

                        for (Beer beer : mArrayList) {

                            if (beer.getName().toLowerCase().contains(charString)) {
                                filteredList.add(beer);
                            }
                        }

                        mFilteredList = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mFilteredList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    mFilteredList = (ArrayList<Beer>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView image;
            private TextView name;
            private TextView price;
            public ViewHolder(View view) {
                super(view);

                image = view.findViewById(R.id.image);
                name = view.findViewById(R.id.name);
                price = view.findViewById(R.id.price);

            }
        }

    }

