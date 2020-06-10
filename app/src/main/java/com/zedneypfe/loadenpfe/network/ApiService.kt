package com.zedneypfe.loadenpfe.network

import com.zedneypfe.loadenpfe.Model.authModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

val KEY: String = "HIE882U6O"

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("sendVerificationCodeSms.php")
    fun getcode(@Query("key") key: String , @Query("phone") phone: String)
            : Call<authModel>

}

val retrofit = Retrofit.Builder()
    .baseUrl("http://demo.zedney.com/application/services/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()