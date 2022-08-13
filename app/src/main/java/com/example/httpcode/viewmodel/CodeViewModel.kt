package com.example.httpcode.viewmodel

import androidx.lifecycle.*
import com.example.httpcode.data.Code
import com.example.httpcode.data.Repository
import kotlinx.coroutines.launch

class CodeViewModel constructor(private val repository: Repository) : ViewModel() {

    private val _codeLiveData = MutableLiveData<List<Code>>()
    val codeLiveData: LiveData<List<Code>> = _codeLiveData

    private fun insertAll(codes: List<Code>?) = viewModelScope.launch {
        if (codes != null) {
            repository.insertAll(codes)
        }
    }

    fun getCodes(){
        viewModelScope.launch {
            val response = repository.getCodesAPI()
            if (response.isSuccessful){
                _codeLiveData.value = response.body()
                // TODO Chamar funcao para salvar no banco
                insertAll(response.body())
            } else {
                // TODO Launch an exception
                // TODO Criar funcao pegaLocal caso esteja sem internet
                _codeLiveData.value = repository.getCodeDB()
            }
        }
    }
}
