package com.example.pintfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpLoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_lover);

        String user = SingletonUsers.Instance().getUser();

        if(user.equals("georg") || user.equals("anita")) {

            TextView text1 = (TextView) findViewById(R.id.search_pub);
            text1.setText("Use the map in the homepage for searching pubs nearby you!\n" +
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

        else{
            TextView first = (TextView) findViewById(R.id.first);
            first.setText("Create your Pub");
            TextView text1 = (TextView) findViewById(R.id.search_pub);
            text1.setText("Just click on the '+' button and start the creation of your pub!\n" +
                    "You will be asked to insert the name, the address, the number of spots, a description and an image.\n" +
                    "The you will procede to the next step in which you can add beers to the menu specifying the price.\n" +
                    "The last step is about defining the opening hours of your Pub :)");

            TextView second = (TextView) findViewById(R.id.second);
            second.setText("Update your Menu");
            TextView text2 = (TextView) findViewById(R.id.choose_pub);
            text2.setText("Of course you will need to modify your menu at a certain point.\n" +
                    "Very easy, you have just to click on the pub and then on Update Menu, an easy interface for adding and removing beers will be dysplayed.");

            TextView third = (TextView) findViewById(R.id.third);
            third.setText("Create an Offer for the Pub");
            TextView text3 = (TextView) findViewById(R.id.manage_beers);
            text3.setText("In order to attract more clients, you will want to create some kind of offers for your Pub!\n" +
                    "You just need to click on Offers in the left drawer, at this point you can add a new offer specifying to which pub to apply the offer, " +
                    "the title and the description of the offer.\n" +
                    "Of course it should be limited in time, so you can add also an expiring date for the offer.");
        }
    }
}
