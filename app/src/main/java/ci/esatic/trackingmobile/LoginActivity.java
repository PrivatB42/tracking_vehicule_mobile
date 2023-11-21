package ci.esatic.trackingmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(view -> {

            if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                Toast.makeText(LoginActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
            }else{
                //proceed to login
                login();
            }

        });
    }


    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString().trim());
        loginRequest.setPassword(password.getText().toString().trim());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService("").userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    Log.d("Response Api :: ", response.body().toString());
                    LoginResponse loginResponse = response.body();
                    Log.d("Login response", loginResponse.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(LoginActivity.this,MainActivity.class)
                                    .putExtra("data",loginResponse.getData().getId())
                                    .putExtra("data2", loginResponse.getData().getUsername()));
                            SharedPreferences sharedPreferences = getSharedPreferences("trackingStorage", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", loginResponse.getData().getToken());
                            editor.putInt("userId", loginResponse.getData().getId());
                            editor.putString("username", loginResponse.getData().getUsername());
                            editor.commit();
                        }
                    },700);

                }else{
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}