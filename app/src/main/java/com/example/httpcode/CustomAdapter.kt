package com.example.httpcode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private val dataSet: ArrayList<Code>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {

        val codeItem = dataSet[position]

        Picasso.get()
            .load(codeItem.image_url)
            .fit()
            .centerCrop()
            .into(holder.image)

    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView

        init {
            image = view.findViewById(R.id.thumbnail)
        }

    }
}


