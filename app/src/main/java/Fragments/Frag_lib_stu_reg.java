package Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import CustomItems.Sugar_user_info;
import in.ac.nith.nithamirpur.Constants;
import in.ac.nith.nithamirpur.Library;
import in.ac.nith.nithamirpur.MainActivity;
import in.ac.nith.nithamirpur.R;


/**
 * Created by Ashu on 15-08-2016.
 */
public class Frag_lib_stu_reg extends Fragment {
    String REGISTER_URL = "http://14.139.56.14/app/student_json.php";
    Button b;
    EditText et;
    TextView mTextView;
    String json_name, json_roll, json_category, json_contact, json_email;
    LinearLayout info;
    TextView name, rollno, category, department, email, contact;

    //    private Realm mRealm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_stu_reg, container, false);
        b = (Button) v.findViewById(R.id.submit);
        et = (EditText) v.findViewById(R.id.et_roll);
        mTextView = (TextView) v.findViewById(R.id.as);
        name = (TextView) v.findViewById(R.id.name);
        rollno = (TextView) v.findViewById(R.id.rollno);
        category = (TextView) v.findViewById(R.id.category);
        email = (TextView) v.findViewById(R.id.email);
        contact = (TextView) v.findViewById(R.id.contact);
        info = (LinearLayout) v.findViewById(R.id.info);
        info.setVisibility(View.INVISIBLE);
//        mRealm=Realm.getInstance(getActivity());
//        mRealm.beginTransaction();
//        Sugar_user_info user=mRealm.createObject(Sugar_user_info.class);
//        user.setName("TP");
//        mRealm.commitTransaction();
//        SharedPreferences pref = getActivity().getSharedPreferences(Library.MY_PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("key_name5", "Ashu");
//        editor.commit();
//        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Library.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
//        editor.putString("name", "Elena");
//        editor.commit();
//
//        Sugar_user_info record=new Sugar_user_info("Ashu");
//        record.save();
//        //if the device is registered
//        if(isRegistered()){
//            startActivity(new Intent(getActivity(), NotificationListener.class));
//            //startActivity(new Intent(getActivity(),NotificationListener.class));
//        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //if the device is not already registered
//                if (!isRegistered()) {
//                    //registering the device
//                    registerDevice();
//                } else {
//                    //if the device is already registered
//                    //displaying a toast
//                    Toast.makeText(getActivity(), "Device Already registered...", Toast.LENGTH_SHORT).show();
//                }
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
                                //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                                try {
                                    //name,barcode,email,mobile,category,
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jresponse = jsonArray.getJSONObject(0);
                                    json_name = jresponse.getString("name");
                                    json_roll = jresponse.getString("barcode");
                                    json_email = jresponse.getString("email");
                                    json_category = jresponse.getString("category");
                                    json_contact = jresponse.getString("mobile");

                                    //String title=jresponse.getString("name");
//                                    record=new Sugar_user_info("Ashu");
//                                    record.save();
                                    name.setText(json_name);
                                    rollno.setText(json_roll);
                                    category.setText(json_category);
                                    contact.setText(json_contact);
                                    email.setText(json_email);
                                    Toast.makeText(getActivity(), json_name, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                        String s = et.getText().toString().trim();
                        params.put("lib_id", s);
                        return params;
                    }
                };

                //Adding the request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(req);
            }


        });

        info.setVisibility(View.VISIBLE);
        return v;
    }
//    private boolean isRegistered() {
//        //Getting shared preferences
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREF, getActivity().MODE_PRIVATE);
//
//        //Getting the value from shared preferences
//        //The second parameter is the default value
//        //if there is no value in sharedprference then it will return false
//        //that means the device is not registered
//        return sharedPreferences.getBoolean(Constants.REGISTERED, false);
//    }
//
//    private void registerDevice() {
//        //Creating a firebase object
//        Firebase firebase = new Firebase(Constants.FIREBASE_APP);
//
//        //Pushing a new element to firebase it will automatically create a unique id
//        Firebase newFirebase = firebase.push();
//
//        //Creating a map to store name value pair
//        Map<String, String> val = new HashMap<>();
//
//        //pushing msg = none in the map
//        val.put("msg", "none");
//
//        //saving the map to firebase
//        newFirebase.setValue(val);
//
//        //Getting the unique id generated at firebase
//        String uniqueId = newFirebase.getKey();
//
//        //Finally we need to implement a method to store this unique id to our server
//        sendIdToServer(uniqueId);
//    }
//
//    private void sendIdToServer(final String uniqueId) {
//        //Creating a progress dialog to show while it is storing the data on server
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Registering device...");
//        progressDialog.show();
//
//        //getting the email entered
//        final String roll_no = et.getText().toString().trim();
//
//        //Creating a string request
//        StringRequest req = new StringRequest(Request.Method.POST, Constants.REGISTER_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //dismissing the progress dialog
//                        progressDialog.dismiss();
//
//                        //if the server returned the string success
//                        if (response.trim().equalsIgnoreCase("success")) {
//                            //Displaying a success toast
//                            Toast.makeText(getActivity(), "Device Registered successfully", Toast.LENGTH_SHORT).show();
//
//                            //Opening shared preference
//                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREF, getActivity().MODE_PRIVATE);
//
//                            //Opening the shared preferences editor to save values
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                            //Storing the unique id
//                            editor.putString(Constants.UNIQUE_ID, uniqueId);
//
//                            //Saving the boolean as true i.e. the device is registered
//                            editor.putBoolean(Constants.REGISTERED, true);
//
//                            //Applying the changes on sharedpreferences
//                            editor.apply();
//
//                            //Starting our listener service once the device is registered
//                            startActivity(new Intent(getActivity(), NotificationListener.class));
//                        } else {
//                            Toast.makeText(getActivity(), "Choose a different email", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                //adding parameters to post request as we need to send firebase id and email
//                params.put("firebaseid", uniqueId);
//                params.put("email", roll_no);
//                return params;
//            }
//        };
//
//        //Adding the request to the queue
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(req);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mRealm.close();
    }
}
