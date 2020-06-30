package com.zedneypfe.loadenpfe.fragments.client

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.getoffres.GetOffres
import com.zedneypfe.loadenpfe.Model.getoffres.QUOTES
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

class MesOffresViewModel (application: Application) : AndroidViewModel(application) {

    val list_offres_getted = MutableLiveData<List<QUOTES>>()

    val process_mesoffres=MutableLiveData<Boolean>()

    //Courotines job+scoope
    //create a courotine : job+scoope+dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    fun getOffres(id: String) {
        coroutineScope.launch {

            val service = retrofit.create(ApiService::class.java)

            val call = service.getOffres(KEY, id)

            call.enqueue(object : retrofit2.Callback<GetOffres> {

                override fun onFailure(call: Call<GetOffres>, t: Throwable) {

                    println("check your internet connexion for mes Offres (msg from mesOffresViewModel)")
                    Toast.makeText(getApplication(),"تحقق من شبكة الانترنت", Toast.LENGTH_LONG).show()


                }//onFailure

                override fun onResponse(
                    call: Call<GetOffres>,
                    response: Response<GetOffres>
                ) {
               //list_getted.value = response.body()!!.result

                    list_offres_getted.value=response.body()!!.result.QUOTES

                    process_mesoffres.value=true

                    println(list_offres_getted)


                }//onResponse
            })//call.enqueue

        }//coroutineScope.launch
    }//fun getDemandes







    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}