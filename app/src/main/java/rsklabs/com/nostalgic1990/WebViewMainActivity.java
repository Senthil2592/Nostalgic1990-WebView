package rsklabs.com.nostalgic1990;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebViewMainActivity extends AppCompatActivity {


    private WebView mWebView;
    ProgressDialog pd = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);


        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "Please check the internet connection!", Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "Here we go.. Please wait this could take few seconds to load", Toast.LENGTH_LONG).show();
            mWebView = (WebView) findViewById(R.id.activity_main_webview);
            mWebView.loadUrl("https://rskinnovationlabs.wordpress.com/2016/03/20/nostalgic-1990s/");
            pd = new ProgressDialog(WebViewMainActivity.this);
            WebChromeClient webChromeClient=new WebChromeClient();
            mWebView.setWebChromeClient(webChromeClient);


            mWebView.setWebViewClient(new NosWebViewClient() {

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pd.setTitle("Please wait");
                    pd.setMessage("Loading Content...");
                    pd.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    pd.dismiss();
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                    Toast.makeText(getBaseContext(), "Bad Connectivity. Please try again",
                            Toast.LENGTH_LONG);
                    view.loadUrl("file:///android_res/drawable/tech_error.jpg");
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }
            });

            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setAppCacheEnabled(false);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setSupportMultipleWindows(true);

        }

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest =new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView1 = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest1 =new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);
    }


    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

       return super.onKeyDown(keyCode, event);
    }



}

