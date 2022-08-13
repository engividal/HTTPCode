package com.example.httpcode.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.httpcode.R
import com.example.httpcode.data.Code
import com.squareup.picasso.Picasso

class CustomAdapter(val context: Context, private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var dataSet: List<Code> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val codeItem = dataSet[position]
        Picasso.get()
            .load("https://http.dog/" + codeItem.image_url)
            .error(R.drawable.code100)
            .fit()
            .centerCrop()
            .into(holder.image)


        holder.bind(codeItem, itemClickListener)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView

        init {
            image = view.findViewById(R.id.thumbnail)
        }

        fun bind(codeItem: Code, itemClickListener: (Int) -> Unit) {
            itemView.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }

    fun setCodeListItems(codeList: List<Code>) {
        this.dataSet = codeList
        notifyDataSetChanged()
    }
}


