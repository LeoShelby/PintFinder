package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // LOGIN BUTTON

        Button signupPubLover = findViewById(R.id.signupPubLover);
        signupPubLover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignUpPubLoverActivity.class);
                startActivity(intent);
            }
        });

        Button signupPubOwner = findViewById(R.id.signupPubOwner);
        signupPubOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignUpPubOwnerActivity.class);
                startActivity(intent);
            }
        });
    }
}
