package com.zedneypfe.loadenpfe.fragments.provider

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.detailDemande.DetailDemande
import com.zedneypfe.loadenpfe.Model.detailDemande.Result
import com.zedneypfe.loadenpfe.network.ApiService
import com.zedneypfe.loadenpfe.network.KEY
import com.zedneypfe.loadenpfe.network.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class DetailsDemandeProviderViewModel(application: Application) : AndroidViewModel(application) {

    val detail_getted = MutableLiveData<Result>()


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getDetailsDemande(id: String) {
        coroutineScope.launch {

            val service = retrofit.create(ApiService::class.java)

            val call = service.getDetailsDemande(KEY, id)

            call.enqueue(object : retrofit2.Callback<DetailDemande> {

                override fun onFailure(call: Call<DetailDemande>, t: Throwable) {

                    println("no detail demande : in DetaildemandeViewModel ")


                }//onFailure

                override fun onResponse(
                    call: Call<DetailDemande>,
                    response: Response<DetailDemande>
                ) {


                    detail_getted.value=response.body()!!.result

                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun getDemandes


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}