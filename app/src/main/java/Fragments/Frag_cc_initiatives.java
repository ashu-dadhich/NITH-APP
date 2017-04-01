package Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import CustomItems.CustomListAdapter_pr;
import CustomItems.Model_companies;
import in.ac.nith.nithamirpur.R;

/**
 * Created by Ashu on 05-09-2016.
 */
public class Frag_cc_initiatives extends Fragment {
    List<String> pdf_url = new ArrayList<>();
    private List<Model_companies> notices = new ArrayList<Model_companies>();
    private ListView listView;
    private CustomListAdapter_pr adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cc_infrastructure, container, false);
        listView = (ListView) v.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getActivity(),"HI"+position,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(pdf_url.get(position)));
                startActivity(i);

            }
        });
        adapter = new CustomListAdapter_pr(getActivity(), notices);
        listView.setAdapter(adapter);

        Model_companies m = new Model_companies();
        m.setData("Network");
        pdf_url.add("http://14.139.56.14/app/cc/pdf/network.pdf");
        //Toast.makeText(getActivity(),m.getData(), Toast.LENGTH_SHORT).show();
        notices.add(m);
        adapter.notifyDataSetChanged();

        Model_companies m1 = new Model_companies();
        m1.setData("Hardware");
        pdf_url.add("http://14.139.56.14/app/cc/pdf/hardware.pdf");
        //Toast.makeText(getActivity(),m.getData(), Toast.LENGTH_SHORT).show();
        notices.add(m1);
        adapter.notifyDataSetChanged();

        Model_companies m2 = new Model_companies();
        m2.setData("Software");
        pdf_url.add("http://14.139.56.14/app/cc/pdf/software.pdf");
        //Toast.makeText(getActivity(),m.getData(), Toast.LENGTH_SHORT).show();
        notices.add(m2);
        adapter.notifyDataSetChanged();
        return v;
    }
}
