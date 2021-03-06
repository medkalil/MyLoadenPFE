package com.zedneypfe.loadenpfe.fragments.provider

import android.app.Application
import android.widget.Toast
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

class LesDemandesViewModel (application: Application) : AndroidViewModel(application) {

    val list_getted = MutableLiveData<List<Result>?>()

    val stat= MutableLiveData<String?>()

    val process_mesdemandes= MutableLiveData<Boolean>()



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
                    Toast.makeText(getApplication(),"تحقق من شبكة الانترنت", Toast.LENGTH_LONG).show()

                }//onFailure

                override fun onResponse(
                    call: Call<MesDemande>,
                    response: Response<MesDemande>
                ) {

                    println("List des demandes")
                    println(response.body()!!)
                    list_getted.value = response.body()!!.result

                    stat.value=response.body()!!.result[0].STAGE_NAME

                    process_mesdemandes.value=true

                    println(list_getted)

                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun getDemandes


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}


