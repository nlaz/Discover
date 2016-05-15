package xyz.nlaz.discover;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nlazaris on 5/12/16.
 */
public class PhotoViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> dates;
    private HashMap<String, MediaItem> objects;

    public PhotoViewAdapter(Context context, ArrayList<String> dates, HashMap<String, MediaItem> objects) {
        this.context = context;
        this.dates = dates;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(dates.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
