package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.app.Activity;
import android.net.Uri;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Jack's App");
        Button first_bt = findViewById(R.id.button_first);
        Button second_bt = findViewById(R.id.button_second);
        Button third_bt = findViewById(R.id.button_third);
        Button fourth_bt= findViewById(R.id.button_fourth);
        Button fifth_bt = findViewById(R.id.button_fifth);
        Button sixth_bt = findViewById(R.id.button_sixth);

        first_bt.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Log.i("clicks","My Face");
            Intent i = new Intent(MainActivity.this, MainActivityFace.class);
            startActivity(i);
        }
        });
        second_bt.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Log.i("clicks","Weather");
            Intent i = new Intent(MainActivity.this, MainActivityWeather.class);
            startActivity(i);
        }
        });
        third_bt.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Log.i("clicks","Facts");
            Intent i = new Intent(MainActivity.this, MainActivityFacts.class);
            startActivity(i);
        }
        });
        fourth_bt.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Log.i("clicks","Invest");
            Intent i = new Intent(MainActivity.this, MainActivityInvest.class);
            startActivity(i);
        }
        });
        fifth_bt.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Log.i("clicks","Resume");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://github.com/thedog747"));
            startActivity(intent);
        }
        });
        sixth_bt.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Log.i("clicks","Bonus Facts");
            Intent i = new Intent(MainActivity.this, MainActivityBonusFacts.class);
            startActivity(i);
        }
        });
    }
}
