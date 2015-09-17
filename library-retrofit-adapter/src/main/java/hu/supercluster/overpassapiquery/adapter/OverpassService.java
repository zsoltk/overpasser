package hu.supercluster.overpassapiquery.adapter;

import retrofit.http.GET;
import retrofit.http.Query;

public interface OverpassService {
    @GET("/interpreter")
    OverpassQueryResult interpreter(@Query("data") String data);
}
