package mandrik.nick.maxelementinterface;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class Dortmund extends Activity {
    SeekBar seekBar;
    TextView textView;
    ImageView imageView;
    Switch switch1;
    Button button;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dortmund);
        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        textView = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView1);

        switch1 = (Switch) findViewById(R.id.switch1);
        button = (Button) findViewById(R.id.button);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }

    public void onMyButtonClick(View view) {
        int rating = 0;
        if (switch1.isChecked()) {
            rating = 2;
        }
        if (seekBar.getProgress() > 160 && seekBar.getProgress() <= 180) {
            rating += 1;
        } else if (seekBar.getProgress() > 180 && seekBar.getProgress() <= 195)
        {
            rating += 2;
        }
        else if (seekBar.getProgress() > 195)
        {
            rating += 3;
        }
        ratingBar.setRating(rating);
    }


}