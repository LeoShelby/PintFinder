package com.example.pintfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;
import static java.security.AccessController.getContext;

public class FilterableAdapter extends RecyclerView.Adapter<FilterableAdapter.ViewHolder> implements Filterable {

        private Context context;
        private ArrayList<Beer> mArrayList;
        private ArrayList<Beer> mFilteredList;
        private String pubName;

    public Context getContext() {
        return context;
    }

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
                                                Toast.makeText(context, "The beer has been successfully added to the Menu!", LENGTH_SHORT).show();
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
                    if (context instanceof AdvancedSearchActivity) {
                        Intent intent = new Intent(context, BeerDescriptionActivity.class);
                        intent.putExtra("beerName", beer.getName());
                        intent.putExtra("activity", "AdvancedSearchActivity");
                        context.startActivity(intent);
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

                    String charString = charSequence.toString().toLowerCase();

                    if (charString.isEmpty() || charString.equals("1,2,3")) {
                        mFilteredList = mArrayList;
                    } else {

                        ArrayList<Beer> filteredList = new ArrayList<>();

                        if (context instanceof SearchBeerFromDatabase || context instanceof CreateMenuActivity) {
                            for (Beer beer : mArrayList) {
                                if (beer.getName().toLowerCase().contains(charString)) {
                                    filteredList.add(beer);
                                }
                            }
                        }
                        else    {

                            String[] text = charString.split(",");
                            String name = text[0];
                            String type = text[1];
                            String nationality = text[2];
                            for (Beer beer : mArrayList) {
                                if (name.equals("1") && type.equals("2") && nationality.equals("3")) // 0 0 0
                                    filteredList = mArrayList;
                                if (!name.equals("1") && type.equals("2") && nationality.equals("3"))   {   // 1 0 0
                                    if (beer.getName().toLowerCase().contains(name)) {
                                        filteredList.add(beer);
                                    }
                                }
                                if (name.equals("1") && !type.equals("2") && nationality.equals("3"))   { // 0 1 0
                                    if (beer.getType().toLowerCase().contains(type)) {
                                        filteredList.add(beer);
                                    }
                                }
                                if (name.equals("1") && type.equals("2") && !nationality.equals("3"))   { // 0 0 1
                                    if (beer.getNationality().toLowerCase().contains(nationality)) {
                                        filteredList.add(beer);
                                    }
                                }
                                if (!name.equals("1") && !type.equals("2") && nationality.equals("3"))  {  // 1 1 0
                                    if (beer.getName().toLowerCase().contains(name) && beer.getType().toLowerCase().contains(type)) {
                                        filteredList.add(beer);
                                    }
                                }
                                if (!name.equals("1") && type.equals("2") && !nationality.equals("3"))  { // 1 0 1
                                    if (beer.getName().toLowerCase().contains(name) && beer.getNationality().toLowerCase().contains(nationality)) {
                                        filteredList.add(beer);
                                    }
                                }
                                if (name.equals("1") && !type.equals("2") && !nationality.equals("3"))  {  // 0 1 1
                                    if (beer.getType().toLowerCase().contains(type) && beer.getNationality().toLowerCase().contains(nationality)) {
                                        filteredList.add(beer);
                                    }
                                }
                                if (!name.equals("1") && !type.equals("2") && !nationality.equals("3"))  {  // 1 1 1
                                    if (beer.getName().toLowerCase().contains(name) && beer.getType().toLowerCase().contains(type) && beer.getNationality().toLowerCase().contains(nationality)) {
                                        filteredList.add(beer);
                                    }
                                }
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
                    if (mFilteredList.size() == 0) {
                        Toast.makeText(getContext(), "No result found. Try again changing the filters.", Toast.LENGTH_LONG).show();
                    }
                    notifyDataSetChanged();
                }
            };
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView image;
            private TextView name;
            private TextView price;
            private Button check;

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

