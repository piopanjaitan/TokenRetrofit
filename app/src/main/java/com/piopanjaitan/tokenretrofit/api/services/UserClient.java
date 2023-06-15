package com.piopanjaitan.tokenretrofit.api.services;

import com.piopanjaitan.tokenretrofit.api.model.Login;
import com.piopanjaitan.tokenretrofit.api.model.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

   /* @Headers("Content-Type: application/json")
    @POST("signin")
    Call<User> login(@Header("Content-Type") String content_type,
                       @Body Login login);*/

    String URL_BASE = "http://10.20.10.11:3000/api/";
    @Headers("Content-Type: application/json")
    @POST("signin")
    Call<User> getUser(@Body  RequestBody params);


//    @Headers("Content-Type: application/json")
@Headers("Content-Type: application/json")
    @POST("signin")
    Call<User> signin(@Body Login signin);

  /*  @Headers("Content-Type: application/json")
    @POST("signin")
    Call<User> login(@Header("Content-Type") String content_type,
                     @Field ("loginid") String loginid,
                     @Field ("password") String password,
                     @Field ("site") String site);*/


   /* @POST("api/signin")
    Call<User> login(@Body  Login login);
    (@Header("Content-Type") String content_type,
    @Field("invoicecode") String invoicecode,
    @Field("suppliercode") String suppliercode,*/

    @GET("vehicle?0=")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}
