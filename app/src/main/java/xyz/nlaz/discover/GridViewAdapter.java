package xyz.nlaz.discover;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by nlazaris on 5/5/16.
 */
public class GridViewAdapter  extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int resource, ArrayList data) {
        super(context, resource, data);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView  = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ImageItem item = data.get(position);
        holder.imageView.setImageBitmap(item.getImage());
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
