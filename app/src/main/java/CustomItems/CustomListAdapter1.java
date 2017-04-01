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
 * Created by Ashu on 14-08-2016.
 */
public class CustomListAdapter1 extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Model1> item;

    public CustomListAdapter1(Activity activity, List<Model1> item) {
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
            convertView = inflater.inflate(R.layout.list_row1, null);
        TextView d = (TextView) convertView.findViewById(R.id.date);
        TextView q = (TextView) convertView.findViewById(R.id.question);
        TextView a = (TextView) convertView.findViewById(R.id.answer);
        TextView n = (TextView) convertView.findViewById(R.id.name);

        Model1 m = item.get(position);
        d.setText(m.getDate());
        a.setText(m.getAnswer());
        n.setText(m.getName());
        q.setText(m.getQuestion());
        return convertView;
    }
}
