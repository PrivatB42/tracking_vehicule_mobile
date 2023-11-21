package ci.esatic.trackingmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddFuelDetails extends AppCompatActivity {
    private EditText billNoEditText;
    private EditText litreEditText;
    private EditText amountEditText;
    private Button submitButton;
    private UserService registrationApi;
    private  String str = "";
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel_details);

        billNoEditText = findViewById(R.id.billno);
        litreEditText = findViewById(R.id.litre);
        amountEditText = findViewById(R.id.amount);
        submitButton = findViewById(R.id.envoibutton);

        Intent intent = getIntent();
        if (intent != null){
            userId = intent.getIntExtra("userid", 0);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("trackingStorage", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("Token", token);
        registrationApi = ApiClient.getUserService(token);

        submitButton.setOnClickListener(view -> {
            String billNo = billNoEditText.getText().toString();
            Float litre =Float.parseFloat(litreEditText.getText().toString());
            Float amount = Float.parseFloat(amountEditText.getText().toString());

            RegistrationModel registrationModel = new RegistrationModel(billNo, litre, amount);

            Log.d("donnees envoyees : ", registrationModel.toString());
            Log.d("id envoye : ", String.valueOf(userId));

            Call<Void> call = registrationApi.register(userId, registrationModel);


            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddFuelDetails.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                        Intent Vari = new Intent(AddFuelDetails.this,MainActivity.class).putExtra("userid", userId);
                        startActivity(Vari);
                    } else {
                        Toast.makeText(AddFuelDetails.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(AddFuelDetails.this, "Erreur lors de la requête", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
