package com.zedneypfe.loadenpfe.fragments.provider

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.addQuote.Quote
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

    val cordoner_tahmil=MutableLiveData<String>()

    val cordoner_tanzil=MutableLiveData<String>()

    val proccess_detail_provider= MutableLiveData<Boolean>()


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

                    cordoner_tahmil.value=response.body()!!.result.UF_CRM_1589924259.split("|").get(1)

                    cordoner_tanzil.value=response.body()!!.result.UF_CRM_1589924283.split("|").get(1)

                    proccess_detail_provider.value=true


                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun getDemandes

    //ADD Quote
    fun addQuote(dealId: String,provPhone:String,totalPrice:Int) {
        coroutineScope.launch {

            val service = retrofit.create(ApiService::class.java)

            val call = service.addQuote(KEY, dealId,provPhone,totalPrice)

            call.enqueue(object : retrofit2.Callback<Quote> {

                override fun onFailure(call: Call<Quote>, t: Throwable) {

                    println("no detail demande : in DetaildemandeViewModel ")
                    Toast.makeText(getApplication(),"تحقق من شبكة الانترنت",Toast.LENGTH_LONG).show()



                }//onFailure

                override fun onResponse(
                    call: Call<Quote>,
                    response: Response<Quote>
                ) {
                    if (response.body()!!.result != 0){

                        Toast.makeText(getApplication(),"لقد تم ارسال عرضك",Toast.LENGTH_LONG).show()

                    }else{

                        Toast.makeText(getApplication(),"لقد قمت بالفعل بإضافة عرض لهذا الطلب",Toast.LENGTH_LONG).show()

                    }


                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun ADD Quote




    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}