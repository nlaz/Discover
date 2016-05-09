package xyz.nlaz.discover;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int image_id = getIntent().getIntExtra("image_id", 0);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap image = null;

        if (image_id != 0) {
            Uri uri = Uri.withAppendedPath( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Integer.toString(image_id) );
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (imageView != null) {
            imageView.setImageBitmap(image);
        }

    }

}
