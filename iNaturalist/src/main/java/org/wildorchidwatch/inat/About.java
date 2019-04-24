package org.wildorchidwatch.inat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;

public class About extends AppCompatActivity {
    private static final String TAG = "About";

    private TextView mAboutText;
    private INaturalistApp mApp;


    @Override
	protected void onStart()
	{
		super.onStart();
		FlurryAgent.onStartSession(this, INaturalistApp.getAppContext().getString(R.string.flurry_api_key));
		FlurryAgent.logEvent(this.getClass().getSimpleName());
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		FlurryAgent.onEndSession(this);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inat_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.about_this_app);
        
        mApp = (INaturalistApp) getApplicationContext();


        mAboutText = findViewById(R.id.inat_credits);

        StringBuilder credits = new StringBuilder();

        credits.append(getString(R.string.inat_credits));

        // Add per-network credit
        final String[] inatNetworks = mApp.getINatNetworks();

        credits.append("<br/><br/>");
        credits.append(getString(R.string.inat_credits_networks_pre));
        credits.append("<br/><br/>");

        for (String network : inatNetworks) {
            String networkCredit = mApp.getStringResourceByName("network_credit_" + network, "n/a");
            if (networkCredit.equals("n/a")) continue;

            credits.append(networkCredit);
            credits.append("<br/><br/>");
        }

        credits.append(getString(R.string.inat_credits_post));

        mAboutText.setText(Html.fromHtml(credits.toString()));
        mAboutText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
