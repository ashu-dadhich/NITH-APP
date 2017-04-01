package in.ac.nith.nithamirpur;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

//import CustomItems.Sugar_user_info;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;

import Fragments.Frag_blank;
import Fragments.Frag_initiatives;
import Fragments.Frag_lib_about;
import Fragments.Frag_lib_arrival_home;
import Fragments.Frag_lib_faq;
import Fragments.Frag_lib_home;
import Fragments.Frag_lib_newarrival;
import Fragments.Frag_lib_notices;
import Fragments.Frag_lib_search_result;
import Fragments.Frag_lib_stu_reg;

public class Library extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //    Sugar_user_info record;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static String FACEBOOK_URL = "https://www.facebook.com/librarynith";
    public static String FACEBOOK_PAGE_ID = "987507453281102";
    Fragment fragment;
    android.support.v4.app.Fragment fragment1;
    TextView t;
    String url = "http://14.139.56.14/app/dept.php";
    //    private Realm mRealm;
    String numberString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        try {
//            InetAddress.getByName("14.139.56.14").isReachable(1000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            if(!InetAddress.getByName("14.139.56.14").isReachable(1000)){
//                //throw new Exception("Host does not exist::"+ 1000);
//                Toast.makeText(Library.this, "Hi", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        setDefaultFragment();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                        "mailto","library@nith.ac.in", null));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
//                startActivity(Intent.createChooser(emailIntent, "Send email..."));
//
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

//        Sugar_user_info record = new Sugar_user_info("Ashu");
//        record.save();
//        Sugar_user_info load=Sugar_user_info.findById(Sugar_user_info.class,1);
        // t = (TextView) header.findViewById(R.id.user_name);
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        //SharedPreferences.Editor editor = pref.edit();
////        editor.putString("key_name5", "string value");
////        editor.commit();
//        String email=pref.getString("key_name5", null);
        //t.setText("ASHU");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.library, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.fb) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/librarynith/?hc_location=ufi"));
//            startActivity(browserIntent);
//        }else
        if (id == R.id.home) {
            Intent i = new Intent(Library.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lib_about) {
            fragment = new Frag_lib_about();

        } else if (id == R.id.nav_lib_notices) {
            fragment = new Frag_lib_notices();
        } else if (id == R.id.nav_lib_ask_lib) {
            //fragment=new Frag_lib_ask_lib();
            fragment = new Frag_lib_home();
        } else if (id == R.id.nav_lib_faq) {
            fragment = new Frag_lib_faq();
        } else if (id == R.id.nav_lib_initiatives) {
            fragment = new Frag_initiatives();
        } else if (id == R.id.nav_lib_newarrivals) {
            //fragment = new Frag_lib_arrival_home();
//            fragment=new Frag_blank();
//            Intent i = new Intent(this,lib_showwebview.class);
//            i.putExtra("KeyLink","http://14.139.56.14/app/new_arrivals.php");
//            startActivity(i);
            fragment = new lib_showwebview();
            Bundle args = new Bundle();
            args.putString("KeyLink", "http://14.139.56.14/app/new_arrivals.php");
            fragment.setArguments(args);
        } else if (id == R.id.nav_lib_eresouces) {
            boolean installed = appInstalledOrNot("com.elib.nitH");
            if (installed) {
                //This intent will help you to launch if the package is already installed
                Intent LaunchIntent = getPackageManager()
                        .getLaunchIntentForPackage("com.elib.nitH");
                startActivity(LaunchIntent);
                return true;
                //System.out.println("App is already installed on your phone");
            } else {
                // System.out.println("App is not currently installed on your phone");
                //fragment = new Frag_initiatives();
                final String appPackageName = "com.elib.nitH"; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.myframe1, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDefaultFragment() {
        Fragment fragment = new Frag_lib_home();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.myframe1, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void fb(View v) {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    public void mail(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "library@nith.ac.in", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Query");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void web(View v) {
        String url = "http://library.nith.ac.in";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void phone(View view) {
//        final ProgressDialog pDialog = new ProgressDialog(Library.this);
//        pDialog.setMessage("Loading...Contact");
//        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(Library.this);

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ashu", response.toString());
                        try {
                            JSONObject jresponse = response.getJSONObject(0);
                            numberString = jresponse.getString("d_phone");
                            //Toast.makeText(Library.this, numberString, Toast.LENGTH_SHORT).show();
                            //url1=jresponse.getString("d_url");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Library.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                //pDialog.hide();
            }
        });
        queue.add(req);
        String temp = "tel:+91"+numberString;
        if(temp.length()==17){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(temp));
            startActivity(intent);
        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
