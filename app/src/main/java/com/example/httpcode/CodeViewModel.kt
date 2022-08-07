package com.example.httpcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CodeViewModel constructor(private val repository: Repository) : ViewModel() {

    private val _codeLiveData = MutableLiveData<List<Code>>()
    val codeLiveData: LiveData<List<Code>> = _codeLiveData

    fun getCodes(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCodes()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    _codeLiveData.value = response.body()
                } else {
                    // TODO Launch an exception
                }
            }
        }
    }
}
