package ci.esatic.trackingmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddServiceDetails extends AppCompatActivity {

    private EditText billNoEditText;
    private EditText statusEditText;
    private EditText amountEditText;
    private Button submitButton;
    private UserService registrationApi;
    private  String str = "";
    private int userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_details);

        Intent intent = getIntent();
        if (intent != null){

            userid = intent.getIntExtra("userid", 0);
        }

        billNoEditText = findViewById(R.id.billno);
        statusEditText = findViewById(R.id.status);
        amountEditText = findViewById(R.id.amount);
        submitButton = findViewById(R.id.envoibutton);

        SharedPreferences sharedPreferences = getSharedPreferences("trackingStorage", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("Token", token);

        registrationApi = ApiClient.getUserService(token);

        submitButton.setOnClickListener(view -> {
            String billNo = billNoEditText.getText().toString();
            String status = statusEditText.getText().toString();
            Float amount = Float.parseFloat(amountEditText.getText().toString());

            RegistrationModelMaint registrationModelMaint = new RegistrationModelMaint(billNo, status, amount);

            Call<Void> call = registrationApi.registermaint(userid, registrationModelMaint);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddServiceDetails.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                        Intent Vari = new Intent(AddServiceDetails.this,MainActivity.class).putExtra("userid", userid);
                        startActivity(Vari);
                    } else {
                        Toast.makeText(AddServiceDetails.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(AddServiceDetails.this, "Erreur lors de la requête", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
