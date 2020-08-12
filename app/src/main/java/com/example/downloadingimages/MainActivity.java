package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.BitSet;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public void downloadImage(View view) {
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            myImage = task.execute("https://vignette.wikia.nocookie.net/antiheroes/images/8/84/AECBDD7A-DA39-4E6B-BBB4-411458AD9FF5.png/revision/latest?cb=20190910161106").get();

            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;

            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
