package com.zedneypfe.loadenpfe.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.getContact.Contact
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MyAccountViewModel(application: Application) : AndroidViewModel(application) {

    val name = MutableLiveData<String>()
    val last_name = MutableLiveData<String>()
    val phone_getted = MutableLiveData<String>()
    val email_getted = MutableLiveData<String>()



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
                    println(response.body()!!.result)

                    name.value=response.body()!!.result.NAME
                    last_name.value=response.body()!!.result.LAST_NAME
                    //with out jsonobj and jsonarray
                    phone_getted.value=response.body()!!.result.PHONE[0].VALUE
                    email_getted.value=response.body()!!.result.EMAIL[0].VALUE

                }
            })//enqueue


        }//coroutineScope.launch
    }//getaccountinfo

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}