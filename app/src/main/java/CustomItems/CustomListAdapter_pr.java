package CustomItems;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 30-08-2016.
 */
public class CustomListAdapter_pr extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Model_companies> item;

    public CustomListAdapter_pr(Activity activity, List<Model_companies> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_pr, null);
        TextView d = (TextView) convertView.findViewById(R.id.txt);


        Model_companies m = item.get(position);
        d.setText(m.getData());
        return convertView;
    }
}
