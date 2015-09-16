package hu.supercluster.overpassapiquery.app.activity.container;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import hu.supercluster.overpassapiquery.R;

@EActivity(R.layout.fragment_container)
public class ContainerActivity extends AppCompatActivity {
    @Bean
    ContainerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }
}
