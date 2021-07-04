package com.example.loraapp;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ServerConnection {
    Retrofit retrofit;

    interface Connection {
        @GET("/data/{device_code}")
        Call<Response> getValuesDef(@Path("device_code") String device_code);

        @POST("/data/{device_code}")
        Call<Response> getValues(@Path("device_code") String device_code, @Query("amount") int amount);

        @POST("/register/{device_code}")
        Call<StatusResponse> sendPos(@Path("device_code") String device_code, @Body double[] coords);

        @POST("/check/{device_code}")
        Call<StatusResponse> checkPos(@Path("device_code") String device_code, @Body double[] coords);

        @POST("/register")
        Call<StatusResponse> sendDev(@Body String device_code);
    }

    ServerConnection(String url){
        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public void getValuesDef(String device_code){
        Connection conn = retrofit.create(Connection.class);
        Call<Response> call = conn.getValuesDef(device_code);
        Callback<Response> callback = new Callback<Response>(){
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response r = response.body();
                Data [] data = r.data; //TODO: return this?
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("DATA", t.getLocalizedMessage());
            }
        };
        call.enqueue(callback);
    }

    public void getValues(String device_code, int amount){
        Connection conn = retrofit.create(Connection.class);
        Call<Response> call = conn.getValues(device_code, amount);
        Callback<Response> callback = new Callback<Response>(){
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response r = response.body();
                Data [] data = r.data; //TODO: return this?
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("DATA", t.getLocalizedMessage());
            }
        };
        call.enqueue(callback);
    }

    public void sendPos(String device_code, double[]coords){
        Connection conn = retrofit.create(Connection.class);
        Call<StatusResponse> call = conn.sendPos(device_code, coords);
        Callback<StatusResponse> callback = new Callback<StatusResponse>(){
            @Override
            public void onResponse(Call<StatusResponse> call, retrofit2.Response<StatusResponse> response) {
                StatusResponse r = response.body();
                String status = r.status;
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("STATUS", t.getLocalizedMessage());
            }
        };
        call.enqueue(callback);
    }

    public void checkPos(String device_code, double[]coords){
        Connection conn = retrofit.create(Connection.class);
        Call<StatusResponse> call = conn.sendPos(device_code, coords);
        Callback<StatusResponse> callback = new Callback<StatusResponse>(){
            @Override
            public void onResponse(Call<StatusResponse> call, retrofit2.Response<StatusResponse> response) {
                StatusResponse r = response.body();
                String status = r.status;
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("STATUS", t.getLocalizedMessage());
            }
        };
        call.enqueue(callback);
    }

    public void sendDev(String device_code){
        Connection conn = retrofit.create(Connection.class);
        Call<StatusResponse> call = conn.sendDev(device_code);
        Callback<StatusResponse> callback = new Callback<StatusResponse>(){
            @Override
            public void onResponse(Call<StatusResponse> call, retrofit2.Response<StatusResponse> response) {
                StatusResponse r = response.body();
                String status = r.status;
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d("STATUS", t.getLocalizedMessage());
            }
        };
        call.enqueue(callback);
    }
}

