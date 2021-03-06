package xyz.nlaz.discover;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

public class DiscoverActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Cursor cursor;
    public static final int MY_READ_PERMISSION = 1234;
    private String TAG = "DiscoverActivity";
    private HashMap<String, ArrayList<MediaItem>> datesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datesMap = new HashMap<>();
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(getOnItemClickListener());
        checkPermissions();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null)
            drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null)
            navigationView.setNavigationItemSelectedListener(this);
    }

    private AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int image_id = (Integer) parent.getItemAtPosition(position);
                Intent intent = new Intent(DiscoverActivity.this, DetailsActivity.class);
                intent.putExtra("image_id", image_id);

                startActivity(intent);
            }
        };
    }

    private void checkPermissions() {
        int permisssionResult = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permisssionResult != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_READ_PERMISSION);
        } else {
            fetchImages();
        }
    }

    private void fetchImages() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] proj = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        String[] proj = null;
        String orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC";
        Log.d(TAG, "fetchImages: before query");
        cursor = getContentResolver().query(uri, proj, null, null, orderBy);
        Log.d(TAG, "fetchImages: after query");
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                int image_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                String bucket = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                String date_taken = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
                MediaItem item = new MediaItem(image_id, bucket, filePath, date_taken);
                ArrayList<MediaItem> list;
                if (datesMap.containsKey(date_taken)) {
                    list = datesMap.get(date_taken);
                } else {
                    list = new ArrayList<>();
                }
                list.add(item);
                datesMap.put(date_taken, list);
                cursor.moveToNext();
            }
            PhotoViewAdapter photoAdapter = new PhotoViewAdapter(this,dates,map,);
//////            gridAdapter = new GridViewAdapter(this, cursor);
////            Log.d(TAG, "onRequestPermissionsResult: Cursor Adapter Created");
//            gridView.setAdapter(gridAdapter);
        } else {
            Log.d(TAG, "System media store is empty");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (requestCode == MY_READ_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchImages();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null){
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.discover, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
