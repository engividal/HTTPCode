package com.example.httpcode.viewmodel

import androidx.lifecycle.*
import com.example.httpcode.data.Code
import com.example.httpcode.data.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeViewModel constructor(private val repository: Repository) : ViewModel() {

    private val _codeLiveData = MutableLiveData<List<Code>>()
    val codeLiveData: LiveData<List<Code>> = _codeLiveData

    private fun insertAll(codes: List<Code>?) = viewModelScope.launch {
        if (codes != null) {
            repository.insertAll(codes)
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun getCodes(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getCodesAPI()
            if (response.isSuccessful){
                insertAll(response.body())
                _codeLiveData.value = repository.getCodeDB()
            } else {
                throw Exception("Failed coroutine")
                // TODO Launch an exception
                // TODO Criar funcao pegaLocal caso esteja sem internet
                _codeLiveData.value = repository.getCodeDB()
            }
        }
    }
}
