package com.example.httpcode

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.httpcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: CustomAdapter
    lateinit var viewModel: CodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiInterface = ApiInterface.create()
        val repository = Repository(apiInterface)
        viewModel = CodeViewModel(repository)

        recyclerView = findViewById(R.id.recyclerview)

        val itemOnClick: (Int) -> Unit = { position ->
            recyclerView.adapter!!.notifyDataSetChanged()
            Log.i("Debug", position.toString())
            Toast.makeText(this, "$position. item clicked.", Toast.LENGTH_SHORT).show()
        }

        customAdapter = CustomAdapter(this, itemClickListener = itemOnClick)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        viewModel.codeLiveData.observe(this) {
            customAdapter.setCodeListItems(it)
        }

        viewModel.getCodes()

    }
}