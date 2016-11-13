package Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import CustomItems.Model;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 05-09-2016.
 */
public class Frag_cc_complain extends Fragment {
    String g_list[] = {
            "Select Complain Type",
            "Hardware",
            "Networking"
    };
    Spinner s;
    EditText name;
    EditText roll;
    EditText num;
    EditText query;
    Button btn;
    View v;
    String subject;
    String body = "";

    String a_mail;
    String h_mail;
    String n_mail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String url = "http://14.139.56.14/app/cc_contact.php";
        v = inflater.inflate(R.layout.fragment_cc_comlain, container, false);
        s = (Spinner) v.findViewById(R.id.g_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, g_list);
        s.setAdapter(adapter);
        btn = (Button) v.findViewById(R.id.g_btn);
        query = (EditText) v.findViewById(R.id.g_query_et);
        name = (EditText) v.findViewById(R.id.g_name_et);
        num = (EditText) v.findViewById(R.id.g_num_et);
        roll = (EditText) v.findViewById(R.id.g_roll_et);
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...support");
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
                                if (i == 0) {
                                    a_mail = obj.getString("email");
                                } else if (i == 1) {
                                    h_mail = obj.getString("email");
                                } else if (i == 2) {
                                    n_mail = obj.getString("email");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pDialog.hide();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
                Toast.makeText(getActivity(), "No Internet Connection Detected", Toast.LENGTH_SHORT).show();
                //pDialog.hide();
                //error.getMessage().toString()
            }
        });
        queue.add(req);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
                String temp = null;
                if (s.getSelectedItemPosition() == 0) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Alert")
                            .setMessage("Please select a complaint type")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    //return;
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else if (s.getSelectedItemPosition() == 1) {
                    temp = h_mail;
                } else if (s.getSelectedItemPosition() == 2) {
                    temp = n_mail;
                }
                subject = s.getSelectedItem().toString() + " " + name.getText().toString() + roll.getText().toString() + " " + num.getText().toString();
                body = query.getText().toString();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", temp, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                //emailIntent.putExtra(Intent.EXTRA_CC, a_mail);
                emailIntent.putExtra(Intent.EXTRA_CC, new String[]{a_mail});
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        return v;
    }
}
