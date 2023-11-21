package ci.esatic.trackingmobile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LocationApi {
    @POST("localisation/create/chauffeur/{chauffeurId}")
    Call<Void> saveLocation(@Path("chauffeurId") int userId, @Body LocationModel location);
}

