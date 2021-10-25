package com.example.mainactivity;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.content.pm.PackageManager;

import android.location.Criteria;
import android.location.LocationListener;

import android.os.Bundle;

import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;

import android.location.Location;
import android.location.LocationManager;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.MalformedURLException;

import org.json.JSONObject;

import android.os.AsyncTask;

import java.util.Objects;

public class MainActivityWeather extends AppCompatActivity {
    public Criteria criteria = new Criteria();
    public String bestProvider;
    public static final int REQUEST_CODE_PERMISSIONS = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Jack's App");
        URL weather_url_1;
        URL weather_url_2;
        URL weather_url_3;
        URL weather_url_4;
        URL weather_url_5;

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            GetPermission get_permission = new GetPermission();
            get_permission.execute();
        }

        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude=location.getLatitude();
                double longitude=location.getLongitude();
                //String msg="New Latitude: "+latitude + ", New Longitude: "+longitude;
                //Log.i("Position", msg);
            }
        };

        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        bestProvider = String.valueOf(lm.getBestProvider(criteria, true)).toString();
        lm.requestLocationUpdates(bestProvider, 1000, 0, ll);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = 0.0;
        double latitude= 0.0;
        while (location == null){}
        if (location != null) {
            longitude = location.getLongitude();
        }
        if (location != null) {
            latitude = location.getLatitude();
        }

        StringBuilder current_location = new StringBuilder(200);
        current_location.append("https://api.openweathermap.org/data/2.5/weather?lat=");
        current_location.append(String.valueOf(latitude));
        current_location.append("&lon=");
        current_location.append(String.valueOf(longitude));
        current_location.append("&appid=3e7c04d6f3504a87899c52e9b924e9e9&units=imperial");
        Log.i("lat",String.valueOf(latitude));
        Log.i("long",String.valueOf(longitude));

        MyTask Task1 = new MyTask();
        MyTask Task2 = new MyTask();
        MyTask Task3 = new MyTask();
        MyTask Task4 = new MyTask();
        MyTask Task5 = new MyTask();

        try{
            weather_url_1 = new URL(current_location.toString());
            Task1.setUrl(weather_url_1);
            weather_url_2 = new URL("https://api.openweathermap.org/data/2.5/weather?id=5092268&appid=3e7c04d6f3504a87899c52e9b924e9e9&units=imperial");
            Task2.setUrl(weather_url_2);
            weather_url_3 = new URL("https://api.openweathermap.org/data/2.5/weather?q=Tokyo&appid=3e7c04d6f3504a87899c52e9b924e9e9&units=imperial");
            Task3.setUrl(weather_url_3);
            weather_url_4 = new URL("https://api.openweathermap.org/data/2.5/weather?id=2965573&appid=3e7c04d6f3504a87899c52e9b924e9e9&units=imperial");
            Task4.setUrl(weather_url_4);
            weather_url_5 = new URL("https://api.openweathermap.org/data/2.5/weather?id=4943629&appid=3e7c04d6f3504a87899c52e9b924e9e9&units=imperial");
            Task5.setUrl(weather_url_5);
        }catch(MalformedURLException ex){
            //do nothing
        }
        //fans of multithreaded efficiency will not like what i did these next 10 lines
        Task1.execute();
        Task2.execute();
        Task3.execute();
        Task4.execute();
        Task5.execute();
        while(Task1.getWeather().isEmpty()){}
        while(Task2.getWeather().isEmpty()){}
        while(Task3.getWeather().isEmpty()){}
        while(Task4.getWeather().isEmpty()){}
        while(Task5.getWeather().isEmpty()){}
        Task1.setLocation("Your Location");
        TextView ll_1 = findViewById(R.id.l1_l);
        TextView lt_1 = findViewById(R.id.l1_t);
        TextView lw_1 = findViewById(R.id.l1_w);
        TextView ll_2 = findViewById(R.id.l2_l);
        TextView lt_2 = findViewById(R.id.l2_t);
        TextView lw_2 = findViewById(R.id.l2_w);
        TextView ll_3 = findViewById(R.id.l3_l);
        TextView lt_3 = findViewById(R.id.l3_t);
        TextView lw_3 = findViewById(R.id.l3_w);
        TextView ll_4 = findViewById(R.id.l4_l);
        TextView lt_4 = findViewById(R.id.l4_t);
        TextView lw_4 = findViewById(R.id.l4_w);
        TextView ll_5 = findViewById(R.id.l5_l);
        TextView lt_5 = findViewById(R.id.l5_t);
        TextView lw_5 = findViewById(R.id.l5_w);
        ll_1.setText(Task1.getLocation());
        lt_1.setText(Task1.getTemp() + " °F");
        lw_1.setText(Task1.getWeather());
        ll_2.setText(Task2.getLocation());
        lt_2.setText(Task2.getTemp() + " °F");
        lw_2.setText(Task2.getWeather());
        ll_3.setText(Task3.getLocation());
        lt_3.setText(Task3.getTemp() + " °F");
        lw_3.setText(Task3.getWeather());
        ll_4.setText(Task4.getLocation());
        lt_4.setText(Task4.getTemp() + " °F");
        lw_4.setText(Task4.getWeather());
        ll_5.setText(Task5.getLocation());
        lt_5.setText(Task5.getTemp() + " °F");
        lw_5.setText(Task5.getWeather());
    }

    private int requestLocationPermission () {

        boolean foreground = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (foreground) {
            boolean background = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;

            if (background) {
                handleLocationUpdates();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_CODE_PERMISSIONS);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_CODE_PERMISSIONS);
        }
        return 0;
    }

    private void handleLocationUpdates() {
        //foreground and background
        Toast.makeText(getApplicationContext(),"Start Foreground and Background Location Updates",Toast.LENGTH_SHORT).show();
    }

    private class MyTask extends AsyncTask<Void, Void, Void>{
        String result;
        JSONObject json;
        URL url;
        String temp = "";
        String location = "";
        String weather = "";
        public void setUrl(URL passed_url) { url = passed_url; }
        public String getTemp() { return temp; }
        public String getLocation() { return location; }
        public String getWeather() { return weather; }
        public void setTemp(String val) { temp = val; }
        public void setLocation(String val) { location = val; }
        public void setWeather(String val) { weather = val; }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                try{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String stringBuffer;
                    String string = "";
                    while ((stringBuffer = bufferedReader.readLine()) != null){
                        string = String.format("%s%s", string, stringBuffer);
                    }
                    bufferedReader.close();
                    result = string;
                    try{
                        json = new JSONObject(result);
                        temp = json.getJSONObject("main").getString("temp");
                        location = json.getString("name");
                        weather = (json.getJSONArray("weather")).getJSONObject(0).getString("main");
                        //Log.i("Temp (F)",temp);
                        //Log.i("Location",location);
                        //Log.i("Weather",weather);
                    }
                    catch(org.json.JSONException e){
                        Log.e("JSONException", "That JSON is busted.");
                    }

                }catch(MalformedURLException ex){
                    //do nothing
                }
            } catch (IOException e){
                e.printStackTrace();
                result = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            //Log.i("JSON",result);
            super.onPostExecute(aVoid);
        }

    }
    private class GetPermission extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            requestLocationPermission();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
                finish();
            }
            super.onPostExecute(aVoid);
        }

    }
}