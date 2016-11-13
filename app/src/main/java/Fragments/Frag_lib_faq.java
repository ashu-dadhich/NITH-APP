package Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import CustomItems.CustomListAdapter;
import CustomItems.CustomListAdapter1;
import CustomItems.Model;
import CustomItems.Model1;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 14-08-2016.
 */
public class Frag_lib_faq extends Fragment {
    private static final String url = "http://14.139.56.14/app/faq.php";
    private ProgressDialog pDialog;
    private List<Model1> query = new ArrayList<Model1>();
    private ListView listView;
    private CustomListAdapter1 adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_faq, container, false);

        listView = (ListView) v.findViewById(R.id.list1);
        adapter = new CustomListAdapter1(getActivity(), query);
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
                                if (obj.getString("q_faq").equals("1")) {
                                    Model1 m = new Model1();
                                    m.setDate(obj.getString("q_time"));
                                    //Toast.makeText(getActivity(), m.getDate(), Toast.LENGTH_SHORT).show();
                                    m.setAnswer(obj.getString("q_response"));
                                    m.setName(obj.getString("q_name"));
                                    m.setQuestion(obj.getString("q_query"));
                                    query.add(m);
                                }
                                //Toast.makeText(getActivity(),obj.getString("q_faq"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
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
        return v;
    }
}
