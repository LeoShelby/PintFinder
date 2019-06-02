package com.example.pintfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // LOGIN BUTTON
        String user = SingletonUsers.Instance().getUser();

        if(user.equals("anita") || user.equals("georg")) {
            Intent intent = new Intent(WelcomeActivity.this, HomePageLover.class);
            startActivity(intent);
        }

        if(user.equals("maria") || user.equals("paul")){
            Intent intent = new Intent(WelcomeActivity.this, HomePageOwner.class);
            startActivity(intent);
        }

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                }
        });

        // SIGN-UP BUTTON

        Button signup = findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builderr = new AlertDialog.Builder(WelcomeActivity.this);
        builderr.setTitle("Confirm Your Action") //
                .setMessage("Do you really want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
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
}
