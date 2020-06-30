package com.zedneypfe.loadenpfe.fragments.client

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.detailDemande.DetailDemande
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

class DetailsDemandeViewModel (application: Application) : AndroidViewModel(application) {

    val detail_getted = MutableLiveData<com.zedneypfe.loadenpfe.Model.detailDemande.Result>()

    val cordoner_tahmil_client=MutableLiveData<String>()

    val cordoner_tanzil_client=MutableLiveData<String>()

    val proccess_detail_client= MutableLiveData<Boolean>()


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getDetailsDemande(id: String) {
        coroutineScope.launch {

            val service = retrofit.create(ApiService::class.java)

            val call = service.getDetailsDemande(KEY, id)

            call.enqueue(object : retrofit2.Callback<DetailDemande> {

                override fun onFailure(call: Call<DetailDemande>, t: Throwable) {

                    println("no detail demande : in DetaildemandeViewModel ")
                    Toast.makeText(getApplication(),"تحقق من شبكة الانترنت", Toast.LENGTH_LONG).show()


                }//onFailure

                override fun onResponse(
                    call: Call<DetailDemande>,
                    response: Response<DetailDemande>
                ) {


                    detail_getted.value=response.body()!!.result

                    cordoner_tahmil_client.value=response.body()!!.result.UF_CRM_1589924259.split("|").get(1)

                    cordoner_tanzil_client.value=response.body()!!.result.UF_CRM_1589924283.split("|").get(1)

                    proccess_detail_client.value=true

                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun getDemandes


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}