package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Log;

import java.util.Objects;

public class MainActivityFace extends AppCompatActivity {
    ImageView bruh;
    Button bruh_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_face);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Jack's App");
        bruh_bt = findViewById(R.id.button);

        final MediaPlayer bruh_mp = MediaPlayer.create(this, R.raw.bruh_2);

        bruh_bt.setOnClickListener(view -> {
            Log.i("clicks","Bruh");
            bruh=findViewById(R.id.imageView2);
            bruh.setVisibility(View.VISIBLE);
            bruh_mp.start();
        });
    }
}

