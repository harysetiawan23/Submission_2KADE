package com.example.harry.submission_2kade.Api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    fun create(): EndPoint {

        val httpClient = OkHttpClient().newBuilder()
        val interceptor = Interceptor { chain ->
            val request = chain?.request()?.newBuilder()?.addHeader("X-Auth-Token", "9594bdd135834da68c6a066a329578cf")?.build()
            chain?.proceed(request)
        }

        val client =  httpClient.addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .build()
        return retrofit.create(EndPoint::class.java)
    }
}