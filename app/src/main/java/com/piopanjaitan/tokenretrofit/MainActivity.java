package com.piopanjaitan.tokenretrofit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.piopanjaitan.tokenretrofit.api.model.Login;
import com.piopanjaitan.tokenretrofit.api.model.User;
import com.piopanjaitan.tokenretrofit.api.services.ServiceGenerator;
import com.piopanjaitan.tokenretrofit.api.services.UserClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

  /*  private static String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserClient.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserClient apiInterface = retrofit.create(UserClient.class);

        Log.v("cek", "json2 = " + retrofit.toString() + " " + UserClient.URL_BASE);

        // prepare call in Retrofit 2.0
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("loginid", "GCM ADMIN");
            paramObject.put("password", "p@ssw0rd234");
            paramObject.put("site", "GCM");

            Call<User> userCall = apiInterface.getUser(String.valueOf(paramObject));
            userCall.enqueue(this);
            Log.v("cek", "json = " + String.valueOf(paramObject) + " " + userCall.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
            token = response.body().getToken();
        } else {
            Toast.makeText(MainActivity.this, "Login not correct :(", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {

        Toast.makeText(MainActivity.this, "error :( ", Toast.LENGTH_SHORT).show();

    }*/

   private static String token;
   public TextView tvJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_login).setOnClickListener(view -> { login(); });
        findViewById(R.id.btn_secret).setOnClickListener(view -> { getSecret(); });
        tvJson = (TextView) findViewById(R.id.tvJson);
    }

    String loginid, password, site;
//    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    private void login() {
        UserClient service = ServiceGenerator.createService(UserClient.class);

        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("loginid", "GCM ADMIN");

            paramObject.put("password", "p@ssw0rd234");
            paramObject.put("site", "GCM");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestBody reqs =RequestBody.create( MediaType.parse("application/json"), String.valueOf(paramObject));

        Call<User> call = service.getUser(reqs);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.v("cek", String.valueOf(response) + " " + response.body().getToken());
                    Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                } else {
                    Log.v("cek", String.valueOf(response) + " " + response.body().getToken());
                    Toast.makeText(MainActivity.this, "Login not correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("cek",t.toString());
                Toast.makeText(MainActivity.this, call.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

   /* private void getSecret() {
        UserClient service = ServiceGenerator.createService2(UserClient.class);
        Call<ResponseBody> call = service.getSecret(token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.v("cek", String.valueOf(response) + " " + response.body().string());
                        Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                        String viewJson = (response.body().string());
                        tvJson.setText(viewJson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v("cek", String.valueOf(response) + " ");
                    Toast.makeText(MainActivity.this, "token is not correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("cek",t.toString());
                Toast.makeText(MainActivity.this, "error :( ", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

    private void getSecret() {
        UserClient service = ServiceGenerator.createService2(UserClient.class);
        Call<ResponseBody> call = service.getSecret(token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.v("cek", String.valueOf(response) + " " + response.body().string());
                        Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                        String viewJson = (response.body().string());
                        tvJson.setText(viewJson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v("cek", String.valueOf(response) + " ");
                    Toast.makeText(MainActivity.this, "token is not correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("cek",t.toString());
                Toast.makeText(MainActivity.this, "error :( ", Toast.LENGTH_SHORT).show();

            }
        });
    }


}