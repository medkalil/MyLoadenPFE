package com.zedneypfe.loadenpfe.fragments

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.Model.authModel
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


    /*val res = MutableLiveData<String>()*/
    val au = MutableLiveData<authModel>()

    //Courotines job+scoope
    //create a courotine : job+scoope+dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)





  /*   fun getresp(phone: String) {
         coroutineScope.launch {
             //KEY is a cont from
             val service = retrofit.create(ApiService::class.java)

             val call = service.getcode(KEY, phone)

             call.enqueue(object : retrofit2.Callback<authModel> {

                 override fun onFailure(call: Call<authModel>, t: Throwable) {
                     println("failed")
                 }

                 override fun onResponse(call: Call<authModel>, response: Response<authModel>) {
                     println(response.body()!!)
                     //only the code from the authModel
                    // res.value = response.body()!!.verif_code

                     //all the responce ->authModel
                     au.value = response.body()
                 }
             })//enqueue

         }//coroutineScope.launch
     }//getresp()*/

    //clearing the job after clearing the ViewModel
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}