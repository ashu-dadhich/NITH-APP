package in.ac.nith.nithamirpur;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Fragments.Frag_CC;
import Fragments.Frag_cc_about;
import Fragments.Frag_lib_home;

public class CC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc);
        //setDefaultFragment();
    }
//    public void setDefaultFragment() {
//        Fragment fragment = new Frag_cc_about();
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.myframe3, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

}
