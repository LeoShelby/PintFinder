package com.example.pintfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateHoursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hours);

        Button done = findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "The Pub has been successfully created!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateHoursActivity.this, HomePageOwner.class);
                startActivity(intent);
            }
        });



        Spinner spinner1_1 = (Spinner) findViewById(R.id.spinner1_1);
        final Spinner spinner1_2 = (Spinner) findViewById(R.id.spinner1_2);
        final Spinner spinner1_3 = (Spinner) findViewById(R.id.spinner1_3);
        final Spinner spinner2_1 = (Spinner) findViewById(R.id.spinner2_1);
        final Spinner spinner2_2 = (Spinner) findViewById(R.id.spinner2_2);
        final Spinner spinner2_3 = (Spinner) findViewById(R.id.spinner2_3);
        final Spinner spinner3_1 = (Spinner) findViewById(R.id.spinner3_1);
        final Spinner spinner3_2 = (Spinner) findViewById(R.id.spinner3_2);
        final Spinner spinner3_3 = (Spinner) findViewById(R.id.spinner3_3);
        final Spinner spinner4_1 = (Spinner) findViewById(R.id.spinner4_1);
        final Spinner spinner4_2 = (Spinner) findViewById(R.id.spinner4_2);
        final Spinner spinner4_3 = (Spinner) findViewById(R.id.spinner4_3);
        final Spinner spinner5_1 = (Spinner) findViewById(R.id.spinner5_1);
        final Spinner spinner5_2 = (Spinner) findViewById(R.id.spinner5_2);
        final Spinner spinner5_3 = (Spinner) findViewById(R.id.spinner5_3);
        final Spinner spinner6_1 = (Spinner) findViewById(R.id.spinner6_1);
        final Spinner spinner6_2 = (Spinner) findViewById(R.id.spinner6_2);
        final Spinner spinner6_3 = (Spinner) findViewById(R.id.spinner6_3);
        final Spinner spinner7_1 = (Spinner) findViewById(R.id.spinner7_1);
        final Spinner spinner7_2 = (Spinner) findViewById(R.id.spinner7_2);
        final Spinner spinner7_3 = (Spinner) findViewById(R.id.spinner7_3);

        spinner1_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner1_2.setEnabled(false);
                    spinner1_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner1_2.setEnabled(true);
                    spinner1_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner2_2.setEnabled(false);
                    spinner2_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner2_2.setEnabled(true);
                    spinner2_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner3_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner3_2.setEnabled(false);
                    spinner3_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner3_2.setEnabled(true);
                    spinner3_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner4_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner4_2.setEnabled(false);
                    spinner4_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner4_2.setEnabled(true);
                    spinner4_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner5_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner5_2.setEnabled(false);
                    spinner5_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner5_2.setEnabled(true);
                    spinner5_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner6_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner6_2.setEnabled(false);
                    spinner6_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner6_2.setEnabled(true);
                    spinner6_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner7_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Closed"))
                {
                    spinner7_2.setEnabled(false);
                    spinner7_3.setEnabled(false);
                }
                if(selectedItem.equals("Open"))
                {
                    spinner7_2.setEnabled(true);
                    spinner7_3.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}
