package Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import CustomItems.CustomListAdapter_pr;
import CustomItems.Model_companies;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 08-09-2016.
 */
public class Frag_student_activities extends Fragment {
    String[] name = {
            "Placements",
            "Srijan",
            "SPEC",
            "CSEC",
            "ISTE",
            "SPIC MACAY",
            "CSOC",
            "Prayas",
            "GLUG",
            "NCC",
            "CSS",
            "English Club"
    };
    String[] url1 = {
            "http://www.nith.ac.in/tpo/",
            "http://www.srijan-nith.com/",
            "http://glug.nith.ac.in/",
            "http://csec.nith.ac.in/",
            "http://www.nith.ac.in/iste/index.html",
            "http://www.nith.ac.in/spicmacay/index.html",
            "http://www.csoc.in/",
            "http://www.nith.ac.in/prayas/",
            "http://glug.nith.ac.in/",
            "http://www.nith.ac.in/ncc/index.php",
            "http://www.nith.ac.in/css/css.htm",
            "https://sites.google.com/site/engclub4u"
    };
    private List<Model_companies> notices = new ArrayList<Model_companies>();
    private ListView listView;
    private CustomListAdapter_pr adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_activities, container, false);
        listView = (ListView) v.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url1[position]));
                startActivity(i);
            }
        });
        adapter = new CustomListAdapter_pr(getActivity(), notices);
        listView.setAdapter(adapter);
//            Model_companies m = new Model_companies();
//           // m.setData(name[i]);
////           m.setData("Ashu");
//            //Toast.makeText(getActivity(),m.getData(), Toast.LENGTH_SHORT).show();
//            notices.add(m);
//            adapter.notifyDataSetChanged();

        for (int i = 0; i < 12; i++) {
            Model_companies m = new Model_companies();
            m.setData(name[i]);
            //m.setData("Ashu");
            //Toast.makeText(getActivity(),m.getData(), Toast.LENGTH_SHORT).show();
            notices.add(m);
            adapter.notifyDataSetChanged();
        }
        return v;
    }
}
