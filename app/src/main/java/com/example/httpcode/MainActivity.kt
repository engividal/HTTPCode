package com.example.httpcode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.httpcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        var data = ArrayList<Code>()

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(getData(data))

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    private fun getData(data: ArrayList<Code>): ArrayList<Code> {
        val list: List<String> = listOf("100", "101", "102", "103", "200", "201", "202", "203", "204", "205", "206", "207", "208", "218", "226", "300", "301", "302", "303", "304", "305", "306", "307", "308", "400", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417", "418", "419", "420", "421", "422", "423", "424", "425", "426", "428", "429", "430", "431", "440", "444", "449", "450", "451" )

        list.forEach {
            data.add(Code(
                "https://http.dog/" + it + ".jpg",
                it,
                it
            ))
            Log.i("Debug", "https://http.dog/" + it + ".jpg")
        }

        return data
    }

}