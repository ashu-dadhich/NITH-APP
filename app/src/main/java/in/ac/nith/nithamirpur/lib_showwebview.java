package in.ac.nith.nithamirpur;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import Fragments.Frag_lib_newarrival;

/**
 * Created by Ashu on 31-08-2016.
 */
public class lib_showwebview extends Fragment {
    Fragment fragment;
    LinearLayout l;
    private String curURL;
    private ProgressDialog progressBar;

//    public void init(String url) {
//
//        curURL = url;
//
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,

                             Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.lib_webview, container, false);
        curURL = getArguments().getString("KeyLink");
        progressBar = ProgressDialog.show(getActivity(), "Please wait", "Loading...");
        if (curURL != null) {

            WebView webview = (WebView) view.findViewById(R.id.lib_webView);

            webview.getSettings().setJavaScriptEnabled(true);

            webview.setWebViewClient(new webClient());

            webview.loadUrl(curURL);

        }
        l = (LinearLayout) view.findViewById(R.id.search);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Frag_lib_newarrival();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.myframe1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;

    }

    public void updateUrl(String url) {
        curURL = url;
        WebView webview = (WebView) getView().findViewById(R.id.lib_webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

    private class webClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;

        }

        public void onPageFinished(WebView view, String url) {
            //Log.i(TAG, "Finished loading URL: " + url);
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }

    }

}
