package com.example.httpcode.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.httpcode.R
import com.example.httpcode.data.ApiInterface
import com.example.httpcode.data.CodeRoomDatabase
import com.example.httpcode.data.Repository
import com.example.httpcode.databinding.ActivityMainBinding
import com.example.httpcode.viewmodel.CodeViewModel
import com.example.httpcode.viewmodel.CodeViewModelFactory

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
        val database by lazy { CodeRoomDatabase.getDatabase(this) }

        val repository = Repository(apiInterface, database.codeDao())

        val viewModel: CodeViewModel by viewModels {
            CodeViewModelFactory(repository)
        }

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