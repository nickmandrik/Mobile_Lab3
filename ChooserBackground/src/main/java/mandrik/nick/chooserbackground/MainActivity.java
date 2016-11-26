package mandrik.nick.chooserbackground;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_IMG = 1;
    String imgDecodable;

    private Button bgButton;
    public RelativeLayout relativeLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bgButton = (Button) findViewById(R.id.button1);
        bgButton.setOnClickListener(this);
        context = MainActivity.this;
        relativeLayout = (RelativeLayout)findViewById(R.id.relativelayout);
    }



    @Override
    public void onClick(View view) {
        final CharSequence[] items = {getText(R.string.blue), getText(R.string.pink),getText(R.string.green), getText(R.string.obzor)
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.message);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0: {
                        relativeLayout.setBackgroundResource(R.color.blueColor);
                        Toast.makeText(context, R.string.blue, Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 1: {
                        relativeLayout.setBackgroundResource(R.color.pinkColor);
                        Toast.makeText(context, R.string.pink,
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 2: {
                        relativeLayout.setBackgroundResource(R.color.greenColor);
                        Toast.makeText(context, R.string.green, Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText(context, R.string.obzor, Toast.LENGTH_LONG).show();
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, RESULT_IMG);

                        break;
                    }
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_IMG && resultCode == RESULT_OK
                    && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodable = cursor.getString(columnIndex);
                cursor.close();
                relativeLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), BitmapFactory
                        .decodeFile(imgDecodable)));


            } else {
                Toast.makeText(this,  R.string.not_choose,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
