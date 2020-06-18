package com.zedneypfe.loadenpfe.fragments

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class SignInViewModel(application: Application) : AndroidViewModel(application) {


    val code = MutableLiveData<String>()
    val user_type = MutableLiveData<String>()

    val au = MutableLiveData<authModel>()

    var phone_existed = MutableLiveData<Boolean>()

    //Courotines job+scoope
    //create a courotine : job+scoope+dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getresp(phone: String) {
        coroutineScope.launch {
            //KEY is a cont from
            val service = retrofit.create(ApiService::class.java)

            //TODO  : pass sign_in_number.text
            //  to this fragment(VerifSigninFragment)
            val call = service.getcode(KEY, phone)

            call.enqueue(object : retrofit2.Callback<authModel> {

                override fun onFailure(call: Call<authModel>, t: Throwable) {
                    println("failed")
                }

                override fun onResponse(call: Call<authModel>, response: Response<authModel>) {
                    println(response.body()!!)

                    if (response.body()!!.result == "ok") {
                        phone_existed.value = true

                        user_type.value=response.body()!!.user_type

                        //only the code from the authModel
                        code.value = response.body()!!.verif_code



                        //all the responce ->authModel
                        au.value = response.body()!!
                    } else {
                        phone_existed.value = false
                    }

                }//onResponse
            })//enqueue

        }//coroutineScope.launch
    }


    //clearing the job after clearing the ViewModel
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}