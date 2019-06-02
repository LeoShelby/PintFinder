package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String user = SingletonUsers.Instance().getUser();

        TextView user_email = (TextView) findViewById(R.id.email);
        TextView user_name = (TextView) findViewById(R.id.username);
        ImageView user_pic = (ImageView) findViewById(R.id.user_pic);
        TextView vat_label = (TextView) findViewById(R.id.vatLabel);
        TextView vat_number = (TextView) findViewById(R.id.vatNumber);

        if(user.equals("georg")){
            user_name.setText("Georg Zimmer");
            user_email.setText("georg_zimmer@gmail.com");
            user_pic.setImageDrawable(getResources().getDrawable(R.drawable.profilepic));
            vat_label.setVisibility(View.INVISIBLE);
            vat_number.setVisibility(View.INVISIBLE);
        }
        if(user.equals("anita")){
            user_name.setText("Anita Smith");
            user_email.setText("anita_88@gmail.com");
            user_pic = (ImageView) findViewById(R.id.user_pic);
            user_pic.setImageDrawable(getResources().getDrawable(R.drawable.anita));
            vat_label.setVisibility(View.INVISIBLE);
            vat_number.setVisibility(View.INVISIBLE);
        }
        if(user.equals("paul")){
            user_name.setText("Paul West");
            user_email.setText("pualino_zurk@gmail.com");
            user_pic = (ImageView) findViewById(R.id.user_pic);
            user_pic.setImageDrawable(getResources().getDrawable(R.drawable.paul));
            vat_number.setText("39284571638");
        }
        if(user.equals("maria")){
            user_name.setText("Maria Pitt");
            user_email.setText("mary_pop@gmail.com");
            user_pic = (ImageView) findViewById(R.id.user_pic);
            user_pic.setImageDrawable(getResources().getDrawable(R.drawable.maria));
            vat_number.setText("82749612819");
        }

        Button button = findViewById(R.id.log_out_profile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonUsers.Instance().setUser("");
                Intent intent = new Intent(ProfileActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
