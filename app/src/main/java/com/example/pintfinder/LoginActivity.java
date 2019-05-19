package com.example.pintfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText usernameView = findViewById(R.id.username);

        // LOGIN BUTTON

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameView.getText().toString();


                if (username.equals("georg")) {
                    // PubLover
                    SingletonUsers instance = SingletonUsers.Instance();
                    instance.setUser(username);
                    Intent intent = new Intent(LoginActivity.this, HomePageLover.class);//ListBeersActivity.class);//HomePageLover.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // con questo comando vengono eliminate dalla stack le activity di login in modo che se viene premuto il back button dalla home si esce direttamente dall'app, solo con il bottone di LOG OUT si può tornare all'activity di LOGIN!
                    startActivity(intent);
                }
                else if (username.equals("maria"))  {
                    // Pub Owner
                    SingletonUsers instance = SingletonUsers.Instance();
                    instance.setUser(username);
                    Intent intent = new Intent(LoginActivity.this, HomePageOwner.class); // sostituire MainActivity.class con il nome dell'Activity riguardante l'homepage del PubOwner
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else if (username.equals("anita"))  {
                    // Pub Owner
                    SingletonUsers instance = SingletonUsers.Instance();
                    instance.setUser(username);
                    Intent intent = new Intent(LoginActivity.this, HomePageLover.class); // sostituire MainActivity.class con il nome dell'Activity riguardante l'homepage del PubOwner
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else if (username.equals("paul"))  {
                    // Pub Owner
                    SingletonUsers instance = SingletonUsers.Instance();
                    instance.setUser(username);
                    Intent intent = new Intent(LoginActivity.this, HomePageOwner.class); // sostituire MainActivity.class con il nome dell'Activity riguardante l'homepage del PubOwner
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
