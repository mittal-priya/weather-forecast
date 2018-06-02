package com.example.rakeshredhu.weathercast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rakeshredhu.weathercast.pojo.WeatherPojo;
import com.example.rakeshredhu.weathercast.pojo.Wind;
import com.google.gson.Gson;

public class WeatherActivity extends AppCompatActivity {

    private WeatherPojo mWeatherPojo;
    private TextView mMaxTempTextView;
    private TextView mTemperature;
    private TextView mIntempreature;
    private TextView mHumidity;
    private TextView mPressure;

    private TextView mvisibility;
    private TextView mwindSpeed;
    private TextView mwinddegree;
    private TextView mlatitude;
    private TextView mlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mMaxTempTextView = findViewById(R.id.maxTempDetail);
        mTemperature = findViewById(R.id.temperatureDetail);
        mIntempreature = findViewById(R.id.minTempDetail);
        mHumidity = findViewById(R.id.humidityDetails);
        mPressure = findViewById(R.id.pressureDetail);

        mvisibility = findViewById(R.id.visiblityDetails);
        mwindSpeed = findViewById(R.id.windSpeedDetails);
        mwinddegree = findViewById(R.id.windDegreeDetails);
        mlatitude = findViewById(R.id.Lat);
        mlongitude = findViewById(R.id.Lon);
        String weatherDetails = "";

        Bundle bundle = getIntent().getExtras();
        try {
            weatherDetails = bundle.getString("weather details");
            Log.e("WeatherActivity", " " + weatherDetails);
            Gson gson =new Gson();
            mWeatherPojo = gson.fromJson(weatherDetails, WeatherPojo.class);

        } catch (NullPointerException npe){
            npe.printStackTrace();
        }



        mMaxTempTextView.setText(String.valueOf(covertFareToCel(mWeatherPojo.getMain().getTempMax())));

        mTemperature.setText(String.valueOf(covertFareToCel(mWeatherPojo.getMain().getTemp())));

        mIntempreature.setText(String.valueOf(covertFareToCel(mWeatherPojo.getMain().getTempMin())));

        mHumidity.setText(String.valueOf(mWeatherPojo.getMain().getHumidity()));

        mPressure.setText(String.valueOf(mWeatherPojo.getMain().getPressure()));



        mwindSpeed.setText(String.valueOf(mWeatherPojo.getWind().getSpeed()));

        mvisibility.setText(String.valueOf(visiblityinkm(mWeatherPojo.getVisibility())));

        mwinddegree.setText(String.valueOf(mWeatherPojo.getWind().getDeg()));

        mlatitude.setText(String.valueOf(mWeatherPojo.getCoord().getLat()));

        mlongitude.setText(String.valueOf(mWeatherPojo.getCoord().getLon()));


    }

    private double covertFareToCel(Double temp) {
        return (temp-273.15);
    }
    private double visiblityinkm(double speed){
        return (speed/1000);
    }
}
