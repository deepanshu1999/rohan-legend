package com.example.hp.adjonline;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_full_judgement extends AppCompatActivity {
    private Toolbar toolbar;
    private String link;
    private WebView webView;
    private static final String TAG = "activity_full_judgement";
    private static final int PERMISSION_REQUEST_CODE = 1;
    public ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_judgement);

        Intent intentExtra=getIntent();
        link=intentExtra.getStringExtra("link");
        toolbar = (Toolbar)findViewById(R.id.Resulttoolbar);
        toolbar.setTitle("Document");
        toolbar.setTitleTextAppearance(this,R.style.amaticboldColor);
        final TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Judgment");
        mTitle.setTextColor(Color.BLACK);
        mTitle.setGravity(Gravity.NO_GRAVITY);
        toolbar.setBackgroundColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        webView = (WebView) findViewById(R.id.fullJudgment);
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);


        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
                webView.setDownloadListener(new DownloadListener() {
                    public void onDownloadStart(String url, String userAgent,
                                                String contentDisposition, String mimetype,
                                                long contentLength) {
                        DownloadManager.Request request = new DownloadManager.Request(
                                Uri.parse(url));
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Scilab");
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                    }
                });
            } else {
                // Code for permission

                webView.setDownloadListener(new DownloadListener() {

                    public void onDownloadStart(String url, String userAgent,
                                                String contentDisposition, String mimetype,
                                                long contentLength) {
                        requestPermission();
                        if (checkPermission())
                        {
                            // Code for above or equal 23 API Oriented Device
                            // Your Permission granted already .Do next code

                            DownloadManager.Request request = new DownloadManager.Request(
                                    Uri.parse(url));
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ADJ");
                            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                            dm.enqueue(request);

                        }
                    }
                });
            }
        }
        else
        {

            webView.setDownloadListener(new DownloadListener() {

                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype,
                                            long contentLength) {
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ADJ");
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                }

            });
        }

        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(activity_full_judgement.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity_full_judgement.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            ActivityCompat.requestPermissions(activity_full_judgement.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(activity_full_judgement.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

}

