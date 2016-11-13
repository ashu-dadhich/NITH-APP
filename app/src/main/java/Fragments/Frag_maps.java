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
public class Frag_maps extends Fragment {
    String[] name = {
            "Ekta Cafe",
            "Ground",
            "Open Air Theatre",
            "Administrative Block",
            "Electrical Engineering Department",
            "Computer Science & Engineering",
            "Mechanical Engineering Department",
            "Civil Engineering Department",
            "Architecture",
            "Auditorium",
            "Central Library",
            "Kailash Boys Hostel",
            "Workshop",
            "SBI ATM"
    };
    double[] latitude1 = {
            31.7113155,
            31.7071352,
            31.7057204,
            31.708201,
            31.707945,
            31.708301,
            31.708753,
            31.709041,
            31.709145,
            31.706804,
            31.707142,
            31.7097528,
            31.709745,
            31.709616
    };
    double[] longitude1 = {
            76.5252203,
            76.5256172,
            76.525113,
            76.527983,
            76.527446,
            76.526995,
            76.526808,
            76.527328,
            76.526272,
            76.527446,
            76.526936,
            76.525884,
            76.527078,
            76.5269422
    };
    private List<Model_companies> notices = new ArrayList<Model_companies>();
    private ListView listView;
    private CustomListAdapter_pr adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        listView = (ListView) v.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                double latitude = latitude1[position];
                double longitude = longitude1[position];
                String label = name[position];
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=20";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                ;

            }
        });
        adapter = new CustomListAdapter_pr(getActivity(), notices);
        listView.setAdapter(adapter);

        for (int i = 0; i < 14; i++) {
            Model_companies m = new Model_companies();
            m.setData(name[i]);
            //Toast.makeText(getActivity(),m.getData(), Toast.LENGTH_SHORT).show();
            notices.add(m);
            adapter.notifyDataSetChanged();
        }
        return v;
    }
}
