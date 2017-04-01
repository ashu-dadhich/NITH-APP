package Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import CustomItems.CustomGrid;
import in.ac.nith.nithamirpur.Library;
import in.ac.nith.nithamirpur.R;
import in.ac.nith.nithamirpur.TPO;


public class Frag_grid extends android.support.v4.app.Fragment {

    GridView gridView;
    String[] strings = {
            "About\nNITH",
            "Reach NITH",
            "Maps",
            "Library",
            "TPO",
            "Notices",
            "Computer\nCenter"
    };
    int[] imageId = {
            R.drawable.nithabout,
            R.drawable.howreach,
            R.drawable.maps,
            R.drawable.lib,
            R.drawable.tpo,
            R.drawable.events1,
            R.drawable.logo_cc
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_grid, container, false);
        CustomGrid adapter = new CustomGrid(getActivity(), strings, imageId);
        gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    android.support.v4.app.FragmentManager manager = getFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_about about = new Frag_about();
                    fragmentTransaction.replace(R.id.myframe, about);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 1) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_travel_guide travel = new Frag_travel_guide();
                    fragmentTransaction.replace(R.id.myframe, travel);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else if (position == 2) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_maps maps = new Frag_maps();
                    fragmentTransaction.replace(R.id.myframe, maps);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 3) {
                    Intent i = new Intent(getActivity(), Library.class);
                    startActivity(i);
                } else if (position == 4) {
                    //Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), TPO.class);
                    startActivity(i);
                } else if (position == 5) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_notices notices = new Frag_notices();
                    fragmentTransaction.replace(R.id.myframe, notices);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 6) {
                    FragmentManager manager1 = getFragmentManager();
                    FragmentTransaction fragmentTransaction1 = manager1.beginTransaction();
                    Frag_CC cc = new Frag_CC();
                    fragmentTransaction1.replace(R.id.myframe, cc);
                    fragmentTransaction1.addToBackStack(null);
                    fragmentTransaction1.commit();
                }
            }
        });
        return v;

    }
}



