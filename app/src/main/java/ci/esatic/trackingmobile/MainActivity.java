package ci.esatic.trackingmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageButton imbtn1, imbtn2;
    private Button btn;
    private TextView tv1, tv2;
    private  String str = "";
    private int userid;

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private UserService locationApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.name);
        tv2 = findViewById(R.id.imatriculation);
        findViewById(R.id.imageButton).setOnClickListener(v-> AddFuelD());
        findViewById(R.id.imageButton2).setOnClickListener(v-> AddServiceD());

        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("data2")){
                str = intent.getStringExtra("data2");
            }
            userid = intent.getIntExtra("data", 0);
            tv1.setText(str);
        }


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SharedPreferences sharedPreferences = getSharedPreferences("trackingStorage", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("Token", token);

        locationApi = ApiClient.getUserService(token);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            getLocation();
        }

        findViewById(R.id.sendcoord).setOnClickListener(v-> {

                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
                } else {
                    getLocation();
                }
        });

    }

    private void AddFuelD(){
        Log.d("id a transfere", String.valueOf(userid));
        Intent Vari = new Intent(MainActivity.this,AddFuelDetails.class).putExtra("userid", userid);
        startActivity(Vari);
        finish();
    }

    private void AddServiceD(){
        Log.d("id a transfere", String.valueOf(userid));
        Intent Vari = new Intent(MainActivity.this,AddServiceDetails.class).putExtra("userid", userid);
        startActivity(Vari);
        finish();
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Float latitude = (float) location.getLatitude();
                            Float longitude = (float) location.getLongitude();

                            LocationModel locationModel = new LocationModel(latitude, longitude);

                            Call<Void> call = locationApi.saveLocation(userid,locationModel);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Localisation enregistrée avec succès", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Erreur lors de l'enregistrement de la localisation", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "Erreur lors de la requête", Toast.LENGTH_SHORT).show();
                                    Log.e("LocationActivity", "Erreur : " + t.getMessage());
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Permission de localisation refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
