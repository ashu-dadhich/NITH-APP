package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import CustomItems.CustomGrid;
import in.ac.nith.nithamirpur.R;
import in.ac.nith.nithamirpur.dept_about;

/**
 * Created by Ashu on 08-08-2016.
 */
public class Frag_Departments extends Fragment {
    GridView gridView;
    String[] strings = {
            "Architecture",
            "Chemical",
            "Chemistry",
            "Civil",
            "CSE",
            "ECE",
            "Electrical",
            "Humanities",
            "Management\nStudies",
            "Mathematics",
            "Mechanical",
            "Physics",
    };
    int[] imageId = {
            R.drawable.archi,
            R.drawable.chemist,
            R.drawable.chemical,
            R.drawable.civil,
            R.drawable.computer,
            R.drawable.ece,
            R.drawable.electrical,
            R.drawable.hmm,
            R.drawable.man,
            R.drawable.maths,
            R.drawable.mechanical,
            R.drawable.physics
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_departments, container, false);
        CustomGrid adapter = new CustomGrid(getActivity(), strings, imageId);
        gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 6) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 7) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 8) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 9) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                } else if (position == 10) {
                    Intent intent = new Intent(getActivity(), dept_about.class);
                    String temp = Integer.toString(position);
                    intent.putExtra("keyName", temp);
                    startActivity(intent);
                }
            }
        });
        return v;
    }
}
