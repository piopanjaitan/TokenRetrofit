package com.piopanjaitan.tokenretrofit.api.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gunadi on 08/29/2016.
 */
public class ServiceGenerator {

    //private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static OkHttpClient.Builder httpClient;

    private static Gson gson = new GsonBuilder().setLenient().create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    //                .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)
                    );

    public static <S> S createService(Class<S> serviceClass) {
        String _url = "http://10.20.10.11:3000/api/";
        if (httpClient == null) {


            httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient.addInterceptor(logging);


        }
        OkHttpClient httpclient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .connectTimeout(30, TimeUnit.MINUTES)
                .addInterceptor(addLoggin())
                .build();


        Retrofit retrofit = builder
                .baseUrl(_url)
                .client(httpclient).build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createService2(Class<S> serviceClass) {
        String _url2 = "http://10.20.10.11:3100/api/lov/";
        if (httpClient == null) {


            httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient.addInterceptor(logging);


        }
        OkHttpClient httpclient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .connectTimeout(30, TimeUnit.MINUTES)
                .addInterceptor(addLoggin())
                .build();


        Retrofit retrofit = builder
                .baseUrl(_url2)
                .client(httpclient).build();

        return retrofit.create(serviceClass);
    }


    private static HttpLoggingInterceptor addLoggin() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return logging;
    }

    private OkHttpClient getRequestHeader() {
       /* OkHttpClient httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(20, TimeUnit.SECONDS);
        httpClient.setReadTimeout(30, TimeUnit.SECONDS);

*/
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(360, TimeUnit.SECONDS)
                .connectTimeout(360, TimeUnit.SECONDS)
                .build();

        return httpClient;

    }
}
