package hu.supercluster.overpasser.adapter;

import retrofit.Retrofit;
import retrofit.GsonConverterFactory;

public class OverpassServiceProvider {
    private static OverpassService service;

    public static OverpassService get() {
        if (service == null) {
            service = createService();
        }

        return service;
    }

    private static OverpassService createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://overpass-api.de")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OverpassService.class);
    }
}