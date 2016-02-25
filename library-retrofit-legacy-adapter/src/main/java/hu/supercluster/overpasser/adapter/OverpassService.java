package hu.supercluster.overpasser.adapter;

import retrofit.http.GET;
import retrofit.http.Query;

public interface OverpassService {
    @GET("/api/interpreter")
    OverpassQueryResult interpreter(@Query("data") String data);
}
