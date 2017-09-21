package com.example.android.cardapp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static final int GALLERY_REQUEST = 20;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
    private ImageView backgroundImage;
    private EditText cardMessage, cardFrom;
    private TextView outputMessage, outputFrom;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardMessage = (EditText) findViewById(R.id.message);
        cardFrom = (EditText) findViewById(R.id.from);
        backgroundImage = (ImageView) findViewById(R.id.background);
        imagePath = "";


    }

    public void chooseExistingImages(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        intent.setDataAndType(data, "image/*");

        //start activity and get a result back from it
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST){

                Uri imageUri = data.getData();
                imagePath = imageUri.toString();

                //stream to read the image data
                InputStream iStream;

                try {
                    iStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image =
                            BitmapFactory.decodeStream(iStream);

                    backgroundImage.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to locate image", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void makeCard(View view) {
        Intent intent = new Intent(this, DisplayCard.class);

        String message1 = cardMessage.getText().toString();
        String message2 = cardFrom.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message1);
        intent.putExtra(EXTRA_MESSAGE2, message2);

        intent.putExtra("Bitmap", imagePath);

        startActivity(intent);
    }

    public void resetFields(View view) {
        cardMessage.setText("");
        cardFrom.setText("");
        backgroundImage.setImageResource(android.R.color.transparent);
        imagePath = "";
    }

}
