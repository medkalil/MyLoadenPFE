package com.zedneypfe.loadenpfe.network

import com.zedneypfe.loadenpfe.Model.getContact.Contact
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.Model.mesDemandes.MesDemande
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

val KEY: String = "HIE882U6O"

//added suspend

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("sendVerificationCodeSms.php")
    fun getcode(@Query("key") key: String, @Query("phone") phone: String)
            : Call<authModel>

    @Headers("Content-Type: application/json")
    @POST("getContactByPhoneNumber.php")
    fun getAccountInfo(@Query("key") key: String, @Query("phone") phone: String)
            : Call<Contact>


    //MesDemande(Model MesDemande) a un List des Result to display them in the recycle view
    @Headers("Content-Type: application/json")
    @POST("getDealsByContactPhoneNumber.php")
    fun getDemandes(@Query("key") key: String, @Query("phone") phone: String)
            : Call <MesDemande>


}//interface ApiService

val retrofit = Retrofit.Builder()
    .baseUrl("http://demo.zedney.com/application/services/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()