package ci.esatic.trackingmobile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {


    @POST("chauffeur/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("carburant/create/chauffeur/{chauffeurId}")
    Call<Void> register(@Path("chauffeurId") int userId, @Body RegistrationModel registrationModel);

    @POST("maintenance/create/chauffeur/{chauffeurId}")
    Call<Void> registermaint(@Path("chauffeurId") int userId, @Body RegistrationModelMaint registrationModelMaint);

    @POST("localisation/create/chauffeur/{chauffeurId}")
    Call<Void> saveLocation(@Path("chauffeurId") int userId, @Body LocationModel location);


}
