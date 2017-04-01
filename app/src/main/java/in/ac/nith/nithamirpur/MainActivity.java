package in.ac.nith.nithamirpur;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Fragments.Frag_Departments;
import Fragments.Frag_Students_Corner;
import Fragments.Frag_about;
import Fragments.Frag_blank;
import Fragments.Frag_comming_soon;
import Fragments.Frag_contact;
import Fragments.Frag_grid;
import Fragments.Frag_travel_guide;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public NetworkInfo ni;
    Fragment fragment;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date_checker();
        if (!isNetworkAvailable()) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Connectivity Error")
                    .setMessage("Some features of the app might not work properly as you have no internet connection right now.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setDefaultFragment();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_close) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_website) {
            fragment = new Frag_blank();
            Intent i = new Intent(this, ShowWebView.class);
            i.putExtra("KeyLink", "http://www.nith.ac.in/");
            startActivity(i);
        } else if (id == R.id.nav_maps) {
            double latitude = 31.7084;
            double longitude = 76.5274;
            String label = "NITH";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=20";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            fragment = new Frag_blank();
            Intent chooser = null;
            String share = "Check the NIT Hamirpur App:\n http://nith.ac.in";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, share);
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
            chooser = Intent.createChooser(intent, "Share");
            startActivity(chooser);
        }
//        else if (id == R.id.nav_arr) {
//            fragment = new Frag_blank();
//            Intent i = new Intent(this, ShowWebView.class);
//            i.putExtra("KeyLink", "http://14.139.56.14/app/new_arrivals.php");
//            startActivity(i);
//        }

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.myframe, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void setDefaultFragment() {
//        Fragment fragment = new Frag_grid();
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.myframe, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

    public void date_checker() {
        Date cDate = new Date();
        String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        Log.d("Date", todaysDate);
        String r_day = "2016-08-15";
        String id_ul_zuha = "2016-08-12";
        String midsem_exam_start = "2016-09-19";
        String midsem_exam_end = "2016-09-24";
        String ghandhi_jayanti = "2016-10-02";
        String mid_sem_evaluation = "2016-10-04";
        String test = "2016-09-08";
        String mid_sem_evaluation3 = "2016-10-07";
        String mid_term_break_start = "2016-10-10";
        String mid_term_break_end = "2016-10-16";
        String diwali = "2016-10-30";
        String govardhan = "2016-10-31";
        String hillfair_start = "2016-11-04";
        String hillfair_end = "2016-11-06";
        String guru_nanak_jayanti = "2016-11-14";
        String end_classwork = "2016-11-17";
        String attandance_meet = "2016-11-18";
        String prac_exam_start = "2016-11-21";
        String prac_exam_end = "2016-11-25";
        String end_sem_start = "2016-11-28";
        String end_sem_end = "2016-12-11";
        String winter_break_start = "2016-12-12";
        String milad_un_nabi = "2016-12-13";
        String submissions_of_grade = "2016-12-17";
        String faculty_reporting_date = "2017-01-02";
        String students_reporting_date = "2017-01-03";
        if (todaysDate.equals(r_day)) {
            custom_notify("Holiday:Republic Day");
        } else if (todaysDate.equals(test)) {
            custom_notify("Test notify");
        } else if (todaysDate.equals(id_ul_zuha)) {
            custom_notify("Holiday:Id-Ul-Juha");
        } else if (todaysDate.equals(midsem_exam_start)) {
            custom_notify("Mid Semester Exams Starts");
        } else if (todaysDate.equals(midsem_exam_end)) {
            custom_notify("Mid Semester Exams Ends");
        } else if (todaysDate.equals(ghandhi_jayanti)) {
            custom_notify("Holiday:Ghandhi Jayanti");
        } else if (todaysDate.equals(mid_sem_evaluation)) {
            custom_notify("Mid Semester Evaluation Starts");
        } else if (todaysDate.equals(mid_sem_evaluation3)) {
            custom_notify("Mid Semester Evaluation Ends");
        } else if (todaysDate.equals(mid_term_break_start)) {
            custom_notify("Holidays:Mid Term Break Starts");
        } else if (todaysDate.equals(mid_term_break_end)) {
            custom_notify("Holidays:Mid Term Break Ends");
        } else if (todaysDate.equals(diwali)) {
            custom_notify("Holiday:Diwali");
        } else if (todaysDate.equals(govardhan)) {
            custom_notify("Holiday:Govardhan");
        } else if (todaysDate.equals(hillfair_start)) {
            custom_notify("Fest: Hillffair Starts");
        } else if (todaysDate.equals(hillfair_end)) {
            custom_notify("Fest: Hillffair Ends");
        } else if (todaysDate.equals(guru_nanak_jayanti)) {
            custom_notify("Holiday:Guru Nanak Jayanti");
        } else if (todaysDate.equals(end_classwork)) {
            custom_notify("End Of Class Work");
        } else if (todaysDate.equals(attandance_meet)) {
            custom_notify("Departmental Meeting Regarding Attendance");
        } else if (todaysDate.equals(prac_exam_start)) {
            custom_notify("Exams: Practical Exam Starts");
        } else if (todaysDate.equals(prac_exam_end)) {
            custom_notify("Exams: Practical Exam Ends");
        } else if (todaysDate.equals(end_sem_start)) {
            custom_notify("Exams: End Semester Exams Starts");
        } else if (todaysDate.equals(end_sem_end)) {
            custom_notify("Exams: End Semester Exams Ends");
        } else if (todaysDate.equals(winter_break_start)) {
            custom_notify("Holidays:Winter Break Starts");
        } else if (todaysDate.equals(milad_un_nabi)) {
            custom_notify("Holiday: Milad-Un-Nabi");
        } else if (todaysDate.equals(submissions_of_grade)) {
            custom_notify("Last Date for Submission of Grades");
        } else if (todaysDate.equals(faculty_reporting_date)) {
            custom_notify("Faculty Reporting Date");
        } else if (todaysDate.equals(students_reporting_date)) {
            custom_notify("Students Reporting Date");
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);

            //Opening the shared preferences editor to save values
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Storing the unique id
//                editor.putString(Constants.UNIQUE_ID, uniqueId);

            //Saving the boolean as true i.e. the device is registered
            editor.putBoolean(Constants.REGISTERED, false);

            //Applying the changes on sharedpreferences
            editor.apply();

        }
    }

    public void custom_notify(String text) {
        if (!isRegistered()) {
            //   custom_notify("Holiday:Republic Day");
            //Opening shared preference
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

            // Build notification
            // Actions are just fake
            android.app.Notification noti = new android.app.Notification.Builder(this)
                    .setContentTitle("Notification")
                    .setContentText(text).setSmallIcon(R.drawable.nithlogo)
                    .setContentIntent(pIntent).build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // hide the notification after its selected
            noti.flags |= android.app.Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, noti);

            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);

            //Opening the shared preferences editor to save values
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Storing the unique id
//                editor.putString(Constants.UNIQUE_ID, uniqueId);

            //Saving the boolean as true i.e. the device is registered
            editor.putBoolean(Constants.REGISTERED, true);

            //Applying the changes on sharedpreferences
            editor.apply();

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Frag_grid(), "Home");
        adapter.addFragment(new Frag_Departments(), "Departments");
        adapter.addFragment(new Frag_Students_Corner(), "Students Corner");
        viewPager.setAdapter(adapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean isRegistered() {
        //Getting shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);

        //Getting the value from shared preferences
        //The second parameter is the default value
        //if there is no value in sharedprference then it will return false
        //that means the device is not registered
        return sharedPreferences.getBoolean(Constants.REGISTERED, false);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
