package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 01-09-2016.
 */
public class Frag_tpo_home extends Fragment {
    String t[] = {
            "Every year, more than 50 reputed companies including MNC's visit NIT Hamirpur",
            "Almost all eligible students are placed in companies of International and National repute under campus placement.",
            "More than 75% of total students placed under campus placement.Recruitment for every batch starts from August onwards.Above 30% of students are getting more than one placement.",
            "In every branch about 15% students crack various post graduate exams like CAT, GRE, GATE, GMAT etc. These students get recruited by top universities around the world."
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tpo_home, container, false);

        return v;
    }
}
