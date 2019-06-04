package com.example.pintfinder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

    public class FilterableAdapter extends RecyclerView.Adapter<FilterableAdapter.ViewHolder> implements Filterable {

        private Context context;
        private ArrayList<Beer> mArrayList;
        private ArrayList<Beer> mFilteredList;
        private String pubName;

        public FilterableAdapter(Context context, ArrayList<Beer> arrayList) {
            this.context = context;
            mArrayList = arrayList;
            mFilteredList = arrayList;
        }

        public FilterableAdapter(Context context, ArrayList<Beer> arrayList, String pubName) {
            this.context = context;
            mArrayList = arrayList;
            mFilteredList = arrayList;
            this.pubName = pubName;
        }

        @Override
        public FilterableAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_beer, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final FilterableAdapter.ViewHolder viewHolder, int i) {
            final Beer beer = mFilteredList.get(i);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof SearchBeerFromDatabase) {
                        Intent intent = new Intent(context, AddBeerActivity.class);
                        intent.putExtra("beerName", beer.getName());
                        intent.putExtra("activity", "SearchBeerFromDatabase");
                        context.startActivity(intent);
                    }
                    if (context instanceof CreateMenuActivity) {
                        Log.e("LEOOO","INSTACENOFFF");
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        //builder.setTitle("Title");
                        TextView title = new TextView(context);
                        title.setText("Insert Price (€)");
                        title.setPadding(10, 10, 10, 10);
                        title.setGravity(Gravity.CENTER);
                        title.setTextColor(Color.RED);
                        title.setTextSize(20);

                        builder.setCustomTitle(title);

                        final EditText input = new EditText(context);

                        input.setInputType(InputType.TYPE_CLASS_NUMBER);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //m_Text = input.getText().toString();
                                AlertDialog.Builder builderr = new AlertDialog.Builder(context);
                                builderr.setTitle("") //
                                        .setMessage("Do you really want to add: "+beer.getName() + "to the Menu?") //
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                String price = input.getText().toString();
                                                SingletonBeers.Instance().setPrice(beer,price);
                                                Toast.makeText(context, "The beer has been successfully added to the Menu!", Toast.LENGTH_SHORT).show();
                                                SingletonPubs.Instance().addBeerToMenu(pubName,beer);
                                                mFilteredList.remove(beer);
                                                notifyDataSetChanged();
                                                dialog.dismiss();
                                            }
                                        }) //
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // TODO
                                                dialog.dismiss();
                                            }
                                        });
                                builderr.show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        Dialog d = builder.setView(input).create();

                        d.show();
                        d.getWindow().setLayout(600,500);
                        //builder.show();

                    }
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
            private ImageView check;
            public ViewHolder(View view) {
                super(view);

                image = view.findViewById(R.id.image);
                name = view.findViewById(R.id.name);
                price = view.findViewById(R.id.price);
                check = view.findViewById(R.id.check);
                check.setVisibility(View.GONE);

            }
        }

    }

