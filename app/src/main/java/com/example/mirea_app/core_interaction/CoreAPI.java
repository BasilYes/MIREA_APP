package com.example.mirea_app.core_interaction;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

//https://bootcore.icyftl.ru
public interface CoreAPI {
    @POST("/icYFTL/BootCore/1.0.0/api/user/invoke_email")
    Call<Void> register(@Header("XXX-CODE") String pass, @Query("email") String email);

    @POST("/icYFTL/BootCore/1.0.0/api/user/login")
    Call<Void> login(@Header("XXX-CODE") String pass, @Query("email") String email);

    @POST("/icYFTL/BootCore/1.0.0/api/user/accept_email")
    Call<Void> accept(@Header("XXX-CODE") String pass,@Query("code") String code, @Query("email") String email);
}
