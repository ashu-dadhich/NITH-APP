package Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 08-09-2016.
 */
public class Frag_feedback extends Fragment {
    String REGISTER_URL = "http://14.139.56.14/app/nit_feedback.php";
    EditText e_name;
    EditText e_email;
    EditText e_num;
    EditText e_comments;
    //    EditText roll;
    Button b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
//tpo_rep.php

        e_name = (EditText) v.findViewById(R.id.name);
        e_email = (EditText) v.findViewById(R.id.email);
        e_num = (EditText) v.findViewById(R.id.number);
        e_comments = (EditText) v.findViewById(R.id.comment);
        //      roll=(EditText) v.findViewById(R.id.rollno);
        b = (Button) v.findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Registering device...");
                progressDialog.show();

                //Creating a string request
                StringRequest req = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //dismissing the progress dialog
                                progressDialog.dismiss();

                                //if the server returned the string success
                                if (response.trim().equalsIgnoreCase("success")) {
                                    //Displaying a success toast
                                    Toast.makeText(getActivity(), "Your feedback has been submitted successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getActivity(), "You have already submitted your feedback", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(), "No Internet Connection Detected", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //adding parameters to post request as we need to send firebase id and email
                        params.put("name", e_name.getText().toString().trim());
                        params.put("email", e_email.getText().toString().trim());
                        params.put("mobile", e_num.getText().toString().trim());
                        params.put("comments", e_comments.getText().toString().trim());
//                        params.put("roll",roll.getText().toString().trim());
                        return params;
                    }
                };

                //Adding the request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(req);
            }
        });

        return v;
    }

}
