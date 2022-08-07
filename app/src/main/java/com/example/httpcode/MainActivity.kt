package com.example.httpcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: CustomAdapter
    lateinit var viewModel: CodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiInterface.create()
        val repository = Repository(apiInterface)
        viewModel = CodeViewModel(repository)

        recyclerView = findViewById(R.id.recyclerview)
        customAdapter = CustomAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        viewModel.codeLiveData.observe(this) {
            customAdapter.setCodeListItems(it)
        }

        viewModel.getCodes()
    }
}