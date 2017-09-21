package com.example.android.cardapp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DisplayCard extends AppCompatActivity {

    private ImageView fullscreenCard;
    private TextView cardMessageDipslay, cardFromDisplay;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card);

        cardMessageDipslay = (TextView) findViewById(R.id.cardMessageDisplay);
        cardFromDisplay = (TextView) findViewById(R.id.cardFromDisplay);
        fullscreenCard = (ImageView) findViewById(R.id.fullscreenCard);

        Intent intent = getIntent();
        String message1 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        imagePath = intent.getStringExtra("Bitmap");
        System.out.println(imagePath);

        if(imagePath.isEmpty()){
            fullscreenCard.setImageResource(android.R.color.transparent);
            System.out.println("test");
        } else {
            Uri imageUri = Uri.parse(imagePath);

            try {
                InputStream iStream = getContentResolver().openInputStream(imageUri);

                Bitmap image =
                        BitmapFactory.decodeStream(iStream);

                fullscreenCard.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to locate image", Toast.LENGTH_LONG).show();
            }
        }

        cardMessageDipslay.setText(message1);
        cardFromDisplay.setText(message2);
    }
}
