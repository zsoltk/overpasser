package hu.supercluster.overpasser.adapter;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface OverpassService {
    @GET("/api/interpreter")
    Call<OverpassQueryResult> interpreter(@Query("data") String data);
}
