package sample.android.bignerdranch.com.sample;

import java.util.List;

import org.jboss.aerogear.android.pipe.callback.AbstractSupportFragmentCallback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    setUpAlerts();


    }

    private Fragment getAlertsFragment() {
        return Fragments.Builder.buildAlertsFragment();
    }

    private void setUpAlerts() {
        Fragments.Operator.of(this).set(R.id.layout_container, getAlertsFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private static final class MetricDataCallback extends AbstractSupportFragmentCallback<List<Integer>> {
        @Override
        public void onSuccess(List<Integer> metricBuckets) {

        }

        @Override
        public void onFailure(Exception e) {
        }

    }


}