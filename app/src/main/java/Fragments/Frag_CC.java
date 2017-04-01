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

import CustomItems.CustomGrid;
import in.ac.nith.nithamirpur.Library;
import in.ac.nith.nithamirpur.MainActivity;
import in.ac.nith.nithamirpur.R;
import in.ac.nith.nithamirpur.ShowWebView;
import in.ac.nith.nithamirpur.TPO;

/**
 * Created by Ashu on 05-09-2016.
 */
public class Frag_CC extends Fragment {
    GridView gridView;
    String[] strings = {
            "About",
            "Services",
            "Infrastructure",
            "Grievances",
            "Visit\nWebsite"
    };
    int[] imageId = {
            R.drawable.aboutcc,
            R.drawable.services,
            R.drawable.infrastructure,
            R.drawable.complaint,
            R.drawable.website
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cc, container, false);
        CustomGrid adapter = new CustomGrid(getActivity(), strings, imageId);
        //((MainActivity) getActivity()).setActionBarTitle(YOUR_TITLE);
        gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    android.support.v4.app.FragmentManager manager = getFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_cc_about about = new Frag_cc_about();
                    fragmentTransaction.replace(R.id.myframe, about);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 1) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_cc_services services = new Frag_cc_services();
                    fragmentTransaction.replace(R.id.myframe, services);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else if (position == 2) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_cc_initiatives infrastructure = new Frag_cc_initiatives();
                    fragmentTransaction.replace(R.id.myframe, infrastructure);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 3) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    Frag_cc_complain complain = new Frag_cc_complain();
                    fragmentTransaction.replace(R.id.myframe, complain);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (position == 4) {
                    Intent i = new Intent(getActivity(), ShowWebView.class);
                    i.putExtra("KeyLink", "http://www.nith.ac.in/cc/");
                    startActivity(i);
                }
            }
        });
        return v;

    }
}
