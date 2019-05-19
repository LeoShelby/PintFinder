package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SignUpPubOwnerActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pub_owner);

        ImageView imageProfile = (ImageView) findViewById(R.id.pubOwner_image);

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        Button signup = findViewById(R.id.signupAsPubOwner);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpPubOwnerActivity.this, "The registration was successful.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpPubOwnerActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            ImageView imageView = (ImageView) findViewById(R.id.pubOwner_image);
            int resourceId = getResources().getIdentifier("paul", "drawable", getPackageName());
            Picasso.with(this).load(resourceId).fit().into(imageView);

        }
    }
}
