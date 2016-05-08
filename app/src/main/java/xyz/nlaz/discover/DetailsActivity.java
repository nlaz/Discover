package xyz.nlaz.discover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int image_id = getIntent().getIntExtra("image_id", 0);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (image_id != 0) {

        }
    }

}
