package in.ac.nith.nithamirpur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class dept_about extends AppCompatActivity {
    String url[] = {
            "http://www.nith.ac.in/arch/",
            "http://www.nith.ac.in/chemical/",
            "http://www.nith.ac.in/chem/",
            "http://www.nith.ac.in/ced/",
            "http://cse.nith.ac.in/",
            "http://www.nith.ac.in/ece/",
            "http://www.nith.ac.in/eed/",
            "http://www.nith.ac.in/hum_man/",
            "http://www.nith.ac.in/doms/",
            "http://www.nith.ac.in/math/",
            "http://www.nith.ac.in/med/",
            "http://www.nith.ac.in/phy/",
    };
    String postion;
    TextView t;
    ImageView im;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_about);
        postion = getIntent().getExtras().getString("keyName").toString();
//        Toast.makeText(dept_about.this, postion, Toast.LENGTH_SHORT).show();
        t = (TextView) findViewById(R.id.textView2);
        b = (Button) findViewById(R.id.button1);
        im = (ImageView) findViewById(R.id.imageView2);
        //t.setText(getString(R.string.info_archi).toString());

        if (postion.equals("0")) {
//            Toast.makeText(dept_about.this, getString(R.string.info_archi).toString(), Toast.LENGTH_SHORT).show();
            t.setText(getString(R.string.info_archi).toString());
            im.setImageResource(R.drawable.iarch);
//            i.putExtra("KeyLink", url[0]);

        } else if (postion.equals("1")) {
            t.setText(getString(R.string.info_chemical));
            im.setImageResource(R.drawable.ichemical);
            //          i.putExtra("KeyLink", url[1]);

        } else if (postion.equals("2")) {
            t.setText(getString(R.string.info_chemistry));
            im.setImageResource(R.drawable.ichemical);
            //        i.putExtra("KeyLink", url[2]);

        } else if (postion.equals("3")) {
            t.setText(getString(R.string.info_civil));
            im.setImageResource(R.drawable.icivil);
            //      i.putExtra("KeyLink", url[3]);

        } else if (postion.equals("4")) {
            t.setText(getString(R.string.info_cse));
            im.setImageResource(R.drawable.icse);
            //    i.putExtra("KeyLink", url[4]);

        } else if (postion.equals("5")) {
            t.setText(getString(R.string.info_ece));
            im.setImageResource(R.drawable.iece);
            //  i.putExtra("KeyLink", url[5]);

        } else if (postion.equals("6")) {
            t.setText(getString(R.string.info_eee));
            im.setImageResource(R.drawable.ieee);
            //i.putExtra("KeyLink", url[6]);

        } else if (postion.equals("7")) {
            t.setText(getString(R.string.info_hmm));
            im.setImageResource(R.drawable.admin);
            //i.putExtra("KeyLink", url[7]);

        } else if (postion.equals("8")) {
            t.setText(getString(R.string.info_man));
            im.setImageResource(R.drawable.admin);
            //i.putExtra("KeyLink", url[8]);

        } else if (postion.equals("9")) {
            t.setText(getString(R.string.info_maths));
            im.setImageResource(R.drawable.admin);
            // i.putExtra("KeyLink", url[9]);

        } else if (postion.equals("10")) {
            t.setText(getString(R.string.info_mech));
            im.setImageResource(R.drawable.imechnical);
            //i.putExtra("KeyLink", url[10]);

        } else if (postion == "11") {
            t.setText(getString(R.string.info_pysics));
            im.setImageResource(R.drawable.iece);

        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dept_about.this, ShowWebView.class);
                i.putExtra("KeyLink", url[Integer.parseInt(postion)]);
                startActivity(i);
            }
        });
    }
}
