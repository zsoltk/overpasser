package hu.supercluster.overpassapiquery.app.activity.container;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import hu.supercluster.overpassapiquery.R;

@EBean
class ContainerPresenter {
    @RootContext
    ContainerActivity activity;

    @Bean
    ContainerFragmentHandler fragmentHandler;

    void onCreate() {
        fragmentHandler.showFragment(
                fragmentHandler.getMapFragment(),
                R.id.container
        );
    }
}
