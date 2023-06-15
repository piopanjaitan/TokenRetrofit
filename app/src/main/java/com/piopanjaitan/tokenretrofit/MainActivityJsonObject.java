package com.piopanjaitan.tokenretrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.piopanjaitan.tokenretrofit.api.model.User;
import com.piopanjaitan.tokenretrofit.api.services.ServiceGenerator;
import com.piopanjaitan.tokenretrofit.api.services.UserClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityJsonObject extends AppCompatActivity implements Callback<User> {

    private static String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Log.v("cek", String.valueOf(response) + " " + response.body().getToken());
        Toast.makeText(MainActivityJsonObject.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
        token = response.body().getToken();
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.v("cek",t.toString());
        Toast.makeText(MainActivityJsonObject.this, "error :( ", Toast.LENGTH_SHORT).show();
    }
});
//        UserClient apiInterface = retrofit.create(UserClient.class);
        /*RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), (ByteString) apiInterface);*/

       /* HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        UserClient.addInterceptor(logging);
*/

       // Log.v("cek", "json2 = " + retrofit.toString() + " " + UserClient.URL_BASE);

        // prepare call in Retrofit 2.0
        //try {
          //  JSONObject paramObject = new JSONObject();
            //paramObject.put("loginid", "GCM ADMIN");
            //paramObject.put("password", "p@ssw0rd234");
            //paramObject.put("site", "GCM");

            //Call<User> userCall = apiInterface.getUser(paramObject.toString());
            //userCall.enqueue(MainActivityJsonObject);
            //Log.v("cek", "json = " + String.valueOf(paramObject) + " " + userCall.toString());
        //} catch (JSONException e) {
            //e.printStackTrace();
        //}
    }

    private static OkHttpClient getRequestHeader() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(360, TimeUnit.SECONDS)
                .connectTimeout(360, TimeUnit.SECONDS)
                .addInterceptor(addLoggin())
                .build();

        return httpClient;

    }
    private static HttpLoggingInterceptor addLoggin() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }


    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            Toast.makeText(MainActivityJsonObject.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
            token = response.body().getToken();
        } else {
            Toast.makeText(MainActivityJsonObject.this, "Login not correct :(", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {

        Toast.makeText(MainActivityJsonObject.this, "error :( ", Toast.LENGTH_SHORT).show();
    }
}
