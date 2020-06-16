package com.zedneypfe.loadenpfe.fragments

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.Model.getContact.Contact
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class VerifSignInViewModel(application: Application) : AndroidViewModel(application) {


    val phone_getted_toSave = MutableLiveData<String?>()


    //Courotines job+scoope
    //create a courotine : job+scoope+dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getaccountinfo(phone: String) {
        coroutineScope.launch {
            //KEY is a cont from
            val service = retrofit.create(ApiService::class.java)
            val call = service.getAccountInfo(KEY, phone)

            call.enqueue(object : retrofit2.Callback<Contact> {

                override fun onFailure(call: Call<Contact>, t: Throwable) {
                    println("failed to get the account info")

                }

                override fun onResponse(call: Call<Contact>, response: Response<Contact>) {
                   // println(response.body()!!.result)
                    //with out jsonobj and jsonarray
                    phone_getted_toSave.value=response.body()!!.result.PHONE[0].VALUE

                    println(phone_getted_toSave)
                }
            })//enqueue


        }//coroutineScope.launch
    }//getaccountinfo


    //clearing the job after clearing the ViewModel
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}