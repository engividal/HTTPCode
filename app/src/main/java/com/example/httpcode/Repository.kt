package com.example.httpcode

class Repository constructor(private val apiInterface: ApiInterface) {

    suspend fun getCodes() = apiInterface.getCodes()

}