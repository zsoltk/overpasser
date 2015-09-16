package hu.supercluster.overpassapiquery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

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
