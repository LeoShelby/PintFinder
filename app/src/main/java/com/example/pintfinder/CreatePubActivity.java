package com.example.pintfinder;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreatePubActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    private AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pub);

        builder = new AlertDialog.Builder(CreatePubActivity.this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ImageView button = (ImageView) findViewById(R.id.pub_image);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        Button createButton = findViewById(R.id.create_pub_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tName = findViewById(R.id.pub_name);
                TextView tAddress = findViewById(R.id.pub_address);
                TextView tDescription = findViewById(R.id.pub_description);

                String name = tName.getText().toString();
                String address = tAddress.getText().toString();
                String description = tDescription.getText().toString();


                if(name.equals("") || address.equals("")) {
                    builder.setTitle("") //
                            .setMessage("A name and an Address are required.") //
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }
                else {
                    Pub newPub = new Pub(name,address,"created_pub",description,new ArrayList<String>());
                    SingletonPubs.Instance().addPub(newPub);
                    SingletonUsers.Instance().addPub(name);
                    Intent intent = new Intent(CreatePubActivity.this, CreateMenuActivity.class);
                    intent.putExtra("pub_name", name);
                    intent.putExtra("activity", "CreatePubActivity");
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            //questo è tutto codice che serviva se facevi il loading REALE della foto dalla galleria
            /*
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };


            if (ActivityCompat.checkSelfPermission(CreatePubActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) return;


            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            */

            ImageView imageView = (ImageView) findViewById(R.id.pub_image);

            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            int resourceId = getResources().getIdentifier("created_pub", "drawable",getPackageName());
            Picasso.with(this).load(resourceId).fit().into(imageView);

        }


    }
}
