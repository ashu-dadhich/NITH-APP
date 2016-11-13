package Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.ac.nith.nithamirpur.R;
import in.ac.nith.nithamirpur.ShowWebView;

/**
 * Created by Ashu on 13-08-2016.
 */
public class Frag_lib_about extends android.app.Fragment {
    Fragment fragment;
    //Button b;
    TextView th;
    TextView tb;
    String url1 = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_about, container, false);
        //b=(Button) v.findViewById(R.id.abt_url);
        th = (TextView) v.findViewById(R.id.abt_heading);
        tb = (TextView) v.findViewById(R.id.abt_body);
        String url = "http://14.139.56.14/app/dept.php";
        //String url="http://api.androidhive.info/volley/person_array.json";
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ashu", response.toString());
                        try {
                            JSONObject jresponse = response.getJSONObject(0);
                            String title = jresponse.getString("f_name");
                            th.setText(title);
                            String body = jresponse.getString("d_about");
                            tb.setText(body);
                            //url1=jresponse.getString("d_url");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        pDialog.hide();
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
