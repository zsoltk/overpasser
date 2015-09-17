package hu.supercluster.overpassapiquery.api;

import retrofit.http.GET;
import retrofit.http.Query;

public interface OverPassService {
    @GET("/interpreter")
    OverApiResult interpreter(@Query("data") String data);
}
