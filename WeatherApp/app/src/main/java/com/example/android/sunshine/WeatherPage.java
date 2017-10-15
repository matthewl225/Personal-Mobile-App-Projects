package com.example.android.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherPage extends AppCompatActivity {

    private TextView today;
    private Button day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_page);

        Intent intent = getIntent();
        String message1 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        String[] WeatherForecast = message1.split("\\s{3,}");

        today = (TextView) findViewById(R.id.today);
        day1 = (Button) findViewById(R.id.day1);
        day2 = (Button) findViewById(R.id.day2);
        day3 = (Button) findViewById(R.id.day3);
        day4 = (Button) findViewById(R.id.day4);
        day5 = (Button) findViewById(R.id.day5);
        day6 = (Button) findViewById(R.id.day6);
        day7 = (Button) findViewById(R.id.day7);
        day8 = (Button) findViewById(R.id.day8);
        day9 = (Button) findViewById(R.id.day9);
        day10 = (Button) findViewById(R.id.day10);
        day11 = (Button) findViewById(R.id.day11);
        day12 = (Button) findViewById(R.id.day12);
        day13 = (Button) findViewById(R.id.day13);
        day14 = (Button) findViewById(R.id.day14);


        today.setText(WeatherForecast[0]);
        day1.setText(WeatherForecast[0]);
        day2.setText(WeatherForecast[1]);
        day3.setText(WeatherForecast[2]);
        day4.setText(WeatherForecast[3]);
        day5.setText(WeatherForecast[4]);
        day6.setText(WeatherForecast[5]);
        day7.setText(WeatherForecast[6]);
        day8.setText(WeatherForecast[7]);
        day9.setText(WeatherForecast[8]);
        day10.setText(WeatherForecast[9]);
        day11.setText(WeatherForecast[10]);
        day12.setText(WeatherForecast[11]);
        day13.setText(WeatherForecast[12]);
        day14.setText(WeatherForecast[13]);




    }
}
