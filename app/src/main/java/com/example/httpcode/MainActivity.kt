package com.example.httpcode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        customAdapter = CustomAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        val apiInterface = ApiInterface.create().getCode()

        apiInterface.enqueue( object  : Callback<List<Code>>{
            override fun onResponse(call: Call<List<Code>>, response: Response<List<Code>>) {
                Log.d("Debug", response.body()!!.toString())
                if(response?.body() != null) customAdapter.setCodeListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Code>>, t: Throwable) {
            }

        })
    }



}