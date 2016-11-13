package Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import CustomItems.CustomGrid;
import in.ac.nith.nithamirpur.R;
import in.ac.nith.nithamirpur.ShowWebView;

/**
 * Created by Ashu on 08-08-2016.
 */
public class Frag_Students_Corner extends Fragment {
    GridView gridView;
    String[] strings = {
            "Activities",
            "Student\nNotices",
            "App Feedback",
            "Academic Calender"
    };
    int[] imageId = {
            R.drawable.activitities,
            R.drawable.s_notices,
            R.drawable.feed_back,
            R.drawable.callender
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_corner, container, false);
        View v = inflater.inflate(R.layout.fragment_student_corner, container, false);
        CustomGrid adapter = new CustomGrid(getActivity(), strings, imageId);
        gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_student_activities notices = new Frag_student_activities();
                    fragmentTransaction.replace(R.id.myframe, notices);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else if (position == 1) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_student_notices notices = new Frag_student_notices();
                    fragmentTransaction.replace(R.id.myframe, notices);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else if (position == 2) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_feedback notices = new Frag_feedback();
                    fragmentTransaction.replace(R.id.myframe, notices);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 3) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://www.nith.ac.in/acadcal16.pdf"));
                    startActivity(i);
                }
            }
        });
        return v;
    }
}
