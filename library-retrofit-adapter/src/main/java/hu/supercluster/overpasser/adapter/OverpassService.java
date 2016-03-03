package hu.supercluster.overpasser.adapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OverpassService {
    @GET("/api/interpreter")
    Call<OverpassQueryResult> interpreter(@Query("data") String data);
}
