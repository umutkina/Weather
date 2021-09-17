package com.umutkina.breakingbadcharacters.data.api

import com.lemonz.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        private var instance: ApiService? = null

        @Synchronized
        fun getInstance(): ApiService {
            val client: OkHttpClient.Builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                client.addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
            }

            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
                    .create(ApiService::class.java)
            return instance as ApiService
        }
    }
}