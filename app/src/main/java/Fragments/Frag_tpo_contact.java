package Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import in.ac.nith.nithamirpur.MainActivity;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 18-08-2016.
 */
public class Frag_tpo_contact extends Fragment {
    TextView tpo_name, tpo_post, mob_no, email;
    String url = "http://14.139.56.14/app/tpo_rep.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tpo_contact, container, false);
        tpo_name = (TextView) v.findViewById(R.id.tpo_name);
        tpo_post = (TextView) v.findViewById(R.id.tpo_post);
        mob_no = (TextView) v.findViewById(R.id.mob_no);
        email = (TextView) v.findViewById(R.id.email);
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
                            tpo_name.setText(jresponse.getString("name"));
                            tpo_post.setText(jresponse.getString("f_name"));
                            mob_no.setText(jresponse.getString("mobile"));
                            email.setText(jresponse.getString("email"));
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

    @Override
    public void onDetach() {
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        super.onDetach();
    }

}
