package mandrik.nick.makeup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createSecond(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void createThird(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    public void createFourth(View v) {
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }

    public void close(View v) {
        this.finish();
    }
}

