package hu.supercluster.overpasser.adapter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class OverpassServiceProvider {
    private static OverpassService service;

    public static OverpassService get() {
        if (service == null) {
            service = createService();
        }

        return service;
    }

    private static OverpassService createService() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create()
        ;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://overpass-api.de")
                .setConverter(new GsonConverter(gson))
                .build();

        return restAdapter.create(OverpassService.class);
    }
}