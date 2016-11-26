package com.example.nick.lab3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String imageUrl;
    private ImageView imageView;
    private SeekBar seekBar;
    private Bitmap bitmap;
    private int startHeight, startWidth;
    private TextView scaleLabelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageUrl = getResources().getString(R.string.image_url);
        imageView = (ImageView) findViewById(R.id.image_view);
        seekBar = (SeekBar) findViewById(R.id.seek_bar_image_resize);
        scaleLabelTextView = (TextView) findViewById(R.id.change_scale_label);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double scale = (double) (progress + 10) / (seekBar.getMax() + 10);
                int h = (int) (startHeight * scale);
                int w = (int) (startWidth * scale);
                Bitmap resized = Bitmap.createScaledBitmap(bitmap, w, h, true);
                imageView.setImageBitmap(resized);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        loadImage();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        startWidth =imageView.getWidth();
        startHeight=imageView.getHeight();
    }

    public void openInBrowser(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(imageUrl));
        startActivity(i);
    }

    private void loadImage() {
        ImageLoader imgLoader = new ImageLoader();
        imgLoader.execute(imageUrl);
    }

    class ImageLoader extends AsyncTask<String, Void, Bitmap> {
        private ProgressDialog progressDialog;

        @Override
        protected Bitmap doInBackground(String... params) {
            String imageUrl = params[0];
            try {
                URL url = new URL(imageUrl);
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return bmp;
            } catch (IOException e) {
                Log.e("ERROR", "loadImage: ", e);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.dialog_title),
                    getResources().getString(R.string.dialog_body_text), true);

        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            imageView.setImageBitmap(bmp);
            startHeight = imageView.getHeight();
            startWidth = imageView.getWidth();
            bitmap = bmp;
            seekBar.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
            scaleLabelTextView.setVisibility(View.VISIBLE);
        }

    }
}
