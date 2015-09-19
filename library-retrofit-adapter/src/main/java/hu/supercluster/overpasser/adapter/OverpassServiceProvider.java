package hu.supercluster.overpasser.adapter;

import org.androidannotations.annotations.EBean;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@EBean(scope = EBean.Scope.Singleton)
public class OverpassServiceProvider {
    private final OverpassService service;

    public OverpassServiceProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://overpass-api.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OverpassService.class);
    }

    public OverpassService get() {
        return service;
    }
}