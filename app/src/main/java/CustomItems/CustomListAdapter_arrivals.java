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
 * Created by Ashu on 21-08-2016.
 */
public class CustomListAdapter_arrivals extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Model_arrival> item;

    public CustomListAdapter_arrivals(Activity activity, List<Model_arrival> item) {
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
            convertView = inflater.inflate(R.layout.list_row_arrival, null);

        TextView t = (TextView) convertView.findViewById(R.id.title);
        TextView a = (TextView) convertView.findViewById(R.id.author);
        TextView cop = (TextView) convertView.findViewById(R.id.copies);
        TextView c = (TextView) convertView.findViewById(R.id.call_no);
        TextView d = (TextView) convertView.findViewById(R.id.dept);
        TextView p = (TextView) convertView.findViewById(R.id.pub);

        Model_arrival m = item.get(position);
        t.setText(m.getTitle());
        a.setText(m.getAuthor());
        cop.setText(m.getCopies());
        c.setText(m.getCall_no());
        d.setText(m.getDept());
        p.setText(m.getPub());
        return convertView;
    }
}
