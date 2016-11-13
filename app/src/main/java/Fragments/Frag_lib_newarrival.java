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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import CustomItems.Model;
import in.ac.nith.nithamirpur.R;
import in.ac.nith.nithamirpur.lib_showwebview;

/**
 * Created by Ashu on 20-08-2016.
 */
public class Frag_lib_newarrival extends Fragment {
    EditText title, author;
    //TextView error;
    Button search;
    List<String> d_list = new ArrayList<String>();
    List<String> s_list = new ArrayList<String>();
    List<String> quantity_list = new ArrayList<String>();
    //    String d_list[] = {
//            "Select Department",
//            "Archicture",
//            "Civil Engineering",
//            "Chemical Engineering",
//            "Chemistry",
//            "Computer Science and Engineering",
//            "Electronics & Communication",
//            "Energy and Environment",
//            "Electrical Engineering",
//            "Mathematics",
//            "Mechanical Engineering",
//            "Material Science and Engineering",
//            "Physics",
//            "Humanities and Social Sciencies",
//            "Management Studies",
//            "Central Library"
//    };
//    String s_list[] = {
//            "All",
//            "ARCH",
//            "CED",
//            "CHE",
//            "CHEM",
//            "CSE",
//            "E&C",
//            "EE",
//            "EED",
//            "MATH",
//            "MED",
//            "MSE",
//            "PHY",
//            "HSS",
//            "MGMT",
//            "LIB"
//    };
    String m_list[] = {
            "Select Month",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
    };
    String y_list[] = {
            "Select Year",
            "2016",
            "2015"
    };
    Spinner s_dept, s_month, s_year;
    String t, a, d, m, y;
    String REGISTER_URL = "http://14.139.56.14/app/new_arrivals.php";
    Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_newarrival, container, false);
        d_list.add("Select Department");
        s_list.add("All");
        final String url = "http://14.139.56.14/app/d_name.php";
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ashu", response.toString());
                        //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                String temp = obj.getString("d_name");
                                temp += " (";
                                temp += obj.getString("books");
                                temp += ") ";
                                //Toast.makeText(getActivity(), temp, Toast.LENGTH_SHORT).show();
                                d_list.add(temp);
                                s_list.add(obj.getString("s_name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        pDialog.hide();
                        //adapter.notifyDataSetChanged();
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
        s_dept = (Spinner) v.findViewById(R.id.dept);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, d_list);
        s_dept.setAdapter(adapter);
        s_month = (Spinner) v.findViewById(R.id.month);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, m_list);
        s_month.setAdapter(adapter1);
        s_year = (Spinner) v.findViewById(R.id.year);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, y_list);
        s_year.setAdapter(adapter2);

        title = (EditText) v.findViewById(R.id.title);
        author = (EditText) v.findViewById(R.id.author);

        // error=(TextView) v.findViewById(R.id.error);


        //error.setText(t);
        search = (Button) v.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
//                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setMessage("Searching...");
//                progressDialog.show();

                //Creating a string request
//                StringRequest req = new StringRequest(Request.Method.POST, REGISTER_URL,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
                //dismissing the progress dialog
//                                progressDialog.dismiss();
                //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                //error.setText(response.toString());
//                                fragment=new Frag_lib_search_result();
//                                Bundle args = new Bundle();
//                                args.putString("json", response);
//                                fragment.setArguments(args);
//                                FragmentManager manager = getFragmentManager();
//                                FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                                fragmentTransaction.replace(R.id.myframe1, fragment);
//                                fragmentTransaction.addToBackStack(null);
//                                fragmentTransaction.commit();
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
                //adding parameters to post request as we need to send firebase id and email
                t = title.getText().toString().trim();
                a = author.getText().toString().trim();
                d = s_list.get((Integer) s_dept.getSelectedItemPosition());
                if (((Integer) s_month.getSelectedItemPosition()) == 0) {
                    m = "";
                } else {
                    m = s_month.getSelectedItem().toString();
                }
                if (((Integer) s_month.getSelectedItemPosition()) == 0) {
                    y = "";
                } else {
                    y = s_year.getSelectedItem().toString();
                }
//                        params.put("title",t);
//                        params.put("author",a);
//                        params.put("dept",d);
//                        params.put("month",m);
//                        params.put("year",y);

                REGISTER_URL += "?year=" + y + "&month=" + m + "&title=" + t + "&author=" + a + "&dept=" + d;
                Log.d("ashu", REGISTER_URL);
                fragment = new lib_showwebview();
                Bundle args = new Bundle();
                args.putString("KeyLink", REGISTER_URL);
                fragment.setArguments(args);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.myframe1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
//                        return params;
//                    }
//                };
//
//                //Adding the request to the queue
//                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//                requestQueue.add(req);
//            }
        });
        return v;
    }
}
