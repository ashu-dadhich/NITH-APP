package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import CustomItems.CustomListAdapter_arrivals;
import CustomItems.Model_arrival;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 21-08-2016.
 */
public class Frag_lib_search_result extends Fragment {
    LinearLayout l;
    Fragment fragment;
    private List<Model_arrival> query = new ArrayList<Model_arrival>();
    private ListView listView;
    private CustomListAdapter_arrivals adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_search_result, container, false);
        listView = (ListView) v.findViewById(R.id.list1);
        adapter = new CustomListAdapter_arrivals(getActivity(), query);
        listView.setAdapter(adapter);
        String response = getArguments().getString("json");
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                Model_arrival m = new Model_arrival();
                m.setTitle(obj.getString("title"));
                m.setAuthor(obj.getString("author"));
                m.setCopies(obj.getString("copies"));
                m.setCall_no(obj.getString("call_no"));
                m.setDept(obj.getString("dept"));
                m.setPub(obj.getString("pub"));
                query.add(m);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        l = (LinearLayout) v.findViewById(R.id.search);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Frag_lib_newarrival();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.myframe1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}
