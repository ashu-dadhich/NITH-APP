package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import CustomItems.CustomListAdapter1;
import CustomItems.CustomListAdapter_arrivals;
import CustomItems.Model1;
import CustomItems.Model_arrival;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 21-08-2016.
 */
public class Frag_lib_arrival_home extends Fragment {
    private static final String url = "http://14.139.56.14/app/new_arrival.php";
    LinearLayout l;
    Fragment fragment;
    private ProgressDialog pDialog;
    private List<Model_arrival> query = new ArrayList<Model_arrival>();
    private ListView listView;
    private CustomListAdapter_arrivals adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmen_lib_latst_arrival, container, false);
        listView = (ListView) v.findViewById(R.id.list1);
        adapter = new CustomListAdapter_arrivals(getActivity(), query);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ashu", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Model_arrival m = new Model_arrival();
                                m.setTitle(obj.getString("title"));
                                m.setAuthor(obj.getString("author"));
                                m.setCopies(obj.getString("copies"));
                                //Toast.makeText(getActivity(), m.getCopies().toString(), Toast.LENGTH_SHORT).show();
                                m.setCall_no(obj.getString("call_no"));
                                m.setDept(obj.getString("dept"));
                                m.setPub(obj.getString("pub"));
                                query.add(m);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        pDialog.hide();
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "No Internet Connection Detected", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });
        queue.add(req);
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
