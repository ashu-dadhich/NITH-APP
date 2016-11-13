package Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.Random;

import CustomItems.OnSwipeTouchListener;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 14-08-2016.
 */
public class Frag_lib_home extends Fragment {

    static final int MIN_DISTANCE = 150;
    ImageView i1, i2, i3, i4, i5, i6;
    ImageView[] i = {
            i1, i2, i3, i4, i5, i6
    };
    TextView t;
    TextView b;
    int counter = 0;
    int[] scroll = {
            R.drawable.blue,
            R.drawable.grey
    };
    LinearLayout s;
    JSONArray temp;
    private float x1, x2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_home, container, false);
        final String url = "http://14.139.56.14/app/home.php";

        i1 = (ImageView) v.findViewById(R.id.i1);
        i2 = (ImageView) v.findViewById(R.id.i2);
        i3 = (ImageView) v.findViewById(R.id.i3);
        i4 = (ImageView) v.findViewById(R.id.i4);
        i5 = (ImageView) v.findViewById(R.id.i5);
        i6 = (ImageView) v.findViewById(R.id.i6);

        i[0] = i1;
        i[1] = i2;
        i[2] = i3;
        i[3] = i4;
        i[4] = i5;
        i[5] = i6;

        t = (TextView) v.findViewById(R.id.title);
        b = (TextView) v.findViewById(R.id.body);
        s = (LinearLayout) v.findViewById(R.id.swipe);
        s.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                //Toast.makeText(MyActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                //Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
                try {
                    Random r = new Random();
                    int i1 = (r.nextInt(temp.length()) + 0);
                    JSONObject jresponse = temp.getJSONObject(i1);
                    String title = jresponse.getString("i_head");
                    String replacedStr = title.replaceAll("''", "'");
                    t.setText(replacedStr);
                    String body = jresponse.getString("i_desc");
                    b.setText(body);

                    i[counter].setImageResource(scroll[1]);
                    if (counter == 0) {
                        counter = 5;
                    } else {
                        counter--;
                    }
                    i[counter].setImageResource(scroll[0]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onSwipeLeft() {
//                final ProgressDialog pDialog = new ProgressDialog(getActivity());
//                pDialog.setMessage("Loading...");
//                pDialog.show();
//
//                RequestQueue queue = Volley.newRequestQueue(getActivity());
//
//                JsonArrayRequest req = new JsonArrayRequest(url,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                Log.d("ashu", response.toString());
                try {
                    Random r = new Random();
                    int i1 = (r.nextInt(temp.length()) + 0);
                    JSONObject jresponse = temp.getJSONObject(i1);
                    String title = jresponse.getString("i_head");
                    String replacedStr = title.replaceAll("''", "'");
                    t.setText(replacedStr);
                    String body = jresponse.getString("i_desc");
                    b.setText(body);

                    i[counter].setImageResource(scroll[1]);
                    if (counter == 5) {
                        counter = 0;
                    } else {
                        counter++;
                    }
                    i[counter].setImageResource(scroll[0]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                                pDialog.hide();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //VolleyLog.d(TAG, "Error: " + error.getMessage());
//                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                        pDialog.hide();
//                    }
//                });
//                queue.add(req);
//                //Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                // Toast.makeText(MyActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

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
                            temp = response;
                            Random r = new Random();
                            int i1 = (r.nextInt(response.length()) + 0);
                            JSONObject jresponse = response.getJSONObject(i1);
                            String title = jresponse.getString("i_head");
                            String replacedStr = title.replaceAll("''", "'");
                            t.setText(replacedStr);
                            String body = jresponse.getString("i_desc");
                            b.setText(body);
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

//
//    public boolean onTouchEvent(MotionEvent touchevent)
//    {
//        switch (touchevent.getAction())
//        {
//            // when user first touches the screen we get x and y coordinate
//            case MotionEvent.ACTION_DOWN:
//            {
//                x1 = touchevent.getX();
//                y1 = touchevent.getY();
//                break;
//            }
//            case MotionEvent.ACTION_UP:
//            {
//                x2 = touchevent.getX();
//                y2 = touchevent.getY();
//
//               // / /if left to right sweep event on screen
//                if (x1 < x2)
//                {
//                    Toast.makeText(getActivity(), "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
//                }
//
//                // if right to left sweep event on screen
//                if (x1 > x2)
//                {
//                    //Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
//                }
//
//                // if UP to Down sweep event on screen
//                if (y1 < y2)
//                {
//                    //Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
//                }
//
//                /// /if Down to UP sweep event on screen
//                if (y1 > y2)
//                {
//                    //Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
//                }
//                break;
//            }
//        }
//        return false;
//    }
}
