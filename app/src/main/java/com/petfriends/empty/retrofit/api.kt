package com.petfriends.empty.retrofit

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

data class Mock(
    //서버에서 null주면 null 들어가네? string은 그런건가
    @SerializedName("msg")
    val msg: String
)

data class Mock2(
    val mock : Mock
)

interface Api {

    @GET("/mockData")
    suspend fun getMockData(): Mock2



    companion object {
        fun create(): Api {
            val stethoInterceptingClient = OkHttpClient
                .Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.116:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(stethoInterceptingClient)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}