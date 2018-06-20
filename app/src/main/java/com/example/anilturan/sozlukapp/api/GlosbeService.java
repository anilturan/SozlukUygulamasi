package com.example.anilturan.sozlukapp.api;

import com.example.anilturan.sozlukapp.model.Glosbe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GlosbeService {
    @GET("tm")
    Call<Glosbe> getMean(@Query("from") String from, @Query("dest") String dest,@Query("format") String format, @Query("phrase") String phrase);
}
