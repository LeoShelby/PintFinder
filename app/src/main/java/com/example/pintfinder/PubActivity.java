package com.example.pintfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub);

        //setupUI();

        Intent i = getIntent();
        final String title = i.getStringExtra("pub_name");
        String description = i.getStringExtra("pub_description");
        String address = i.getStringExtra("pub_address");
        String image = i.getStringExtra("pub_image");

        String activity = i.getStringExtra("activity");

        final ArrayList<String> menu = i.getStringArrayListExtra("pub_menu");

        TextView tName = findViewById(R.id.pub_name);
        TextView tAddress = findViewById(R.id.pub_address);
        TextView tDescription = findViewById(R.id.pub_description);
        ImageView tImage = findViewById(R.id.pub_image);


        tName.setText(title);
        tAddress.setText(address);
        if(!description.equals(""))tDescription.setText(description);

        int resourceId = getResources().getIdentifier(image, "drawable",getPackageName());
        Picasso.with(this).load(resourceId).fit().into(tImage);


        Button menuButton = findViewById(R.id.menu_button);
        Button deleteButton = findViewById(R.id.delete_pub_button);

        if(activity == null) {
            deleteButton.setText("Book the Pub");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PubActivity.this, BookPubActivity.class);
                    intent.putExtra("pub_name", title);
                    startActivity(intent);
                }
            });

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PubActivity.this, MenuPubActivity.class);
                    intent.putExtra("pub_menu", menu);
                    startActivity(intent);
                }
            });
        }
        else if (activity.equals("ListPubHomeFragment") || activity.equals("UpdateMenuActivity")) {
            menuButton.setText("Update Menu");
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PubActivity.this, UpdateMenuActivity.class);
                    intent.putExtra("pub_menu", menu);
                    intent.putExtra("pub_name",title);
                    //intent.putExtra("activity","ListPubHomeFragment");
                    startActivity(intent);
                }
            });


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builderr = new AlertDialog.Builder(PubActivity.this);
                    builderr.setTitle("") //
                            .setMessage("Do you really want to remove:\n "+ title + "?") //
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //prima rimuovi il pub dall'utente
                                    SingletonUsers.Instance().deletePub(title);
                                    //poi rimuovi il pub dal sistema
                                    Pub pub = SingletonPubs.Instance().findPubByName(title);
                                    SingletonPubs.Instance().deletePub(pub);
                                    Intent intent = new Intent(PubActivity.this, HomePageOwner.class);
                                    startActivity(intent);
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


        }
        else{}

    }

    @Override
    public void onBackPressed() {
        Intent i = getIntent();
        String activity = i.getStringExtra("activity");
        if(activity == null) {
            Intent intent = new Intent(PubActivity.this,HomePageLover.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PubActivity.this,HomePageOwner.class);
            startActivity(intent);
        }

    }

}
