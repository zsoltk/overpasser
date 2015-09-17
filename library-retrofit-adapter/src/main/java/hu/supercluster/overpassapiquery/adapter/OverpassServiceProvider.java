package hu.supercluster.overpassapiquery.adapter;

import org.androidannotations.annotations.EBean;

import retrofit.RestAdapter;

@EBean(scope = EBean.Scope.Singleton)
public class OverpassServiceProvider {
    private final OverpassService service;

    public OverpassServiceProvider() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://overpass-api.de/api")
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build()
        ;

        service = restAdapter.create(OverpassService.class);
    }

    public OverpassService get() {
        return service;
    }
}