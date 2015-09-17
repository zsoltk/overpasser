package hu.supercluster.overpassapiquery.adapter;

import org.androidannotations.annotations.EBean;

import retrofit.RestAdapter;

@EBean(scope = EBean.Scope.Singleton)
public class OverPassServiceProvider {
    private final OverPassService service;

    public OverPassServiceProvider() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://overpass-api.de/api")
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();

        service = restAdapter.create(OverPassService.class);
    }

    public OverPassService get() {
        return service;
    }
}