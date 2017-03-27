package rsklabs.com.nostalgic1990;

import rsklabs.com.nostalgic1990.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pushbots.push.Pushbots;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {

    private Button cherishButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fullscreen);
        Pushbots.sharedInstance().init(this);
        cherishButton = ((Button) findViewById(R.id.cherishMemories));
        cherishButton.setEnabled(true);

        cherishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mi = new Intent(FullscreenActivity.this, WebViewMainActivity.class);
                startActivity(mi);

            }

        });

        AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest =new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView1 = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest1 =new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

      }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
       new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit Application")
                .setMessage("Are you sure you want to close this application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();


                    }

                })
                .setNegativeButton("No", null)
                .show();
        return super.onKeyDown(keyCode, event);
    }

}
