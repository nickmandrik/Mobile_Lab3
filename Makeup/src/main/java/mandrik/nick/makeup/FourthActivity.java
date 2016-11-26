package mandrik.nick.makeup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }

    public void changeBackground(View v) {
        Button btnTab1 = (Button) findViewById(R.id.button18);
        btnTab1.setBackgroundResource(R.drawable.backround);
    }
}
