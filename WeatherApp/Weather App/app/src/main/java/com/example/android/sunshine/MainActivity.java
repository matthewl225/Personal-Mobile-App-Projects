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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        mWeatherLocation = (EditText) findViewById(R.id.user_location);

        // TODO (9) Call loadWeatherData to perform the network request to get the weather
    }

    protected void loadWeatherData(View view) {
        String location = mWeatherLocation.getText().toString();
        if(location != null && !location.isEmpty()) {
            URL weatherSearchUrl = NetworkUtils.buildUrl(location);
            new FetchWeatherTask().execute(weatherSearchUrl);
        }
    }
    // TODO (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData

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
                mWeatherTextView.setText(weatherSearchResults);
            }
        }
    }

    // TODO (5) Create a class that extends AsyncTask to perform network requests
    // TODO (6) Override the doInBackground method to perform your network requests
    // TODO (7) Override the onPostExecute method to display the results of the network request
}