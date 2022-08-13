package com.example.httpcode.data

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository constructor(private val apiInterface: ApiInterface, private val codeDao: CodeDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(codes: List<Code>) {
        for (code in codes) {
            codeDao.insert(code)
        }
    }

    suspend fun getCodesAPI() = withContext(Dispatchers.IO) {
        apiInterface.getCodes()
    }

    suspend fun getCodeDB() = withContext(Dispatchers.IO){
        codeDao.getAllCodes()
    }

    // TODO criar funcao getcodes
}
