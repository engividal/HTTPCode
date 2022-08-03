package com.example.httpcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodeViewModel: ViewModel() {

    private val _codeLiveData = MutableLiveData<List<Code>>()

    val codeLiveData:LiveData<List<Code>> = _codeLiveData

    fun getCodes(){
        val apiInterface = ApiInterface.create().getCode()

        apiInterface.enqueue( object  : Callback<List<Code>> {
            override fun onResponse(call: Call<List<Code>>, response: Response<List<Code>>) {
                Log.d("Debug", response.body()!!.toString())
                if(response?.body() != null) _codeLiveData.value = response.body()!!
            }

            override fun onFailure(call: Call<List<Code>>, t: Throwable) {
            }

        })
    }
}
