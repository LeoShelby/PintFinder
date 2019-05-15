package com.example.pintfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpLoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_lover);

        TextView text1 = (TextView) findViewById(R.id.search_pub);
        text1.setText("Use tha map in the homepage for searching pubs nearby you!\n" +
                "You just need to click on the marker to see the title and you can open the pub by cliking on the title.\n" +
                "In the homepage you have also your last visited pubs, you can also click on them for checking if the Pub Owner has updated its menu :)");

        TextView text2 = (TextView) findViewById(R.id.choose_pub);
        text2.setText("For choosing the best pub you need of course to know which beers such pub offers to the clients!\n" +
                "Once you open the pub you just need to click on the menu button and you can inspect all the beers offered with their characteristics and the price\n" +
                "If you like the Pub, you can Book it with the dedicated button!");

        TextView text3 = (TextView) findViewById(R.id.manage_beers);
        text3.setText("After many beers tried, you do not want to forget about the feeling you had about them.\n" +
                "You can maintain your archive of beers, you have it in the left drawer. You can add there new beers you have tried, leaving " +
                "a comment about the beer and a generic like or dislike.");
    }
}
