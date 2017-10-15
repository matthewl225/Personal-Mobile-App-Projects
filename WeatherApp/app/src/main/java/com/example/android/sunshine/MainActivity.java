/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;
    private EditText mWeatherLocation;
    public static final String EXTRA_MESSAGE = "com.example.weatherapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.weatherapp.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, get a reference to our TextView from xml. Allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        mWeatherLocation = (EditText) findViewById(R.id.user_location);


    }

    protected void loadWeatherData(View view) {
        String location = mWeatherLocation.getText().toString();
        if(location != null && !location.isEmpty()) {
            URL weatherSearchUrl = NetworkUtils.buildUrl(location);
            new FetchWeatherTask().execute(weatherSearchUrl);


        }
    }

    public class FetchWeatherTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL...params) {
            URL searchUrl = params[0];
            String weatherSearchResults = null;
            String[] weatherInfo;
            String finalWeather = "";
            try{
                weatherSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                weatherInfo = OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this, weatherSearchResults);

                for (String weatherData : weatherInfo) {
                    finalWeather = finalWeather + weatherData + "\n\n\n";
                }

                return finalWeather;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return finalWeather;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults) {
            if(weatherSearchResults !=null && !weatherSearchResults.equals("")) {

                Intent intent = new Intent(getApplicationContext(), WeatherPage.class);

                String message1 = weatherSearchResults;

                intent.putExtra(EXTRA_MESSAGE, message1);

                startActivity(intent);
            }
        }
    }


}