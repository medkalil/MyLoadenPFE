package com.zedneypfe.loadenpfe.fragments.client

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.mesDemandes.MesDemande
import com.zedneypfe.loadenpfe.Model.mesDemandes.Result
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MesDemandesViewModel(application: Application) : AndroidViewModel(application) {

    val list_getted = MutableLiveData<List<Result>>()

    //Courotines job+scoope
    //create a courotine : job+scoope+dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getDemandes(phone: String) {
        coroutineScope.launch {

            val service = retrofit.create(ApiService::class.java)

            val call = service.getDemandes(KEY, phone)

            call.enqueue(object : retrofit2.Callback<MesDemande> {

                override fun onFailure(call: Call<MesDemande>, t: Throwable) {

                    println("check your internet connexion for mes demande (msg from MesDemandesViewModel)")

                }//onFailure

                override fun onResponse(
                    call: Call<MesDemande>,
                    response: Response<MesDemande>
                ) {

                    println("List des demandes")
                    println(response.body()!!)
                    list_getted.value = response.body()!!.result

                    println(list_getted)

                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun getDemandes


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}//MesDemandesViewModel