package com.plant.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.plant.R
import com.plant.pojo.DetailTomato

class SpeciesAdapter internal constructor() : RecyclerView.Adapter<SpeciesAdapter.ListViewHolder>() {

    private var detil = emptyList<DetailTomato>()
    private var listener: ((DetailTomato)->Unit)? = null

    fun click(listener: ((DetailTomato)->Unit)){
        this.listener = listener
    }
    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageUrl: ImageView = view.findViewById(R.id.tomato_img)
        val tomato: TextView = view.findViewById(R.id.tomato_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_tomato, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tomato.text = detil[position].common_name
        Glide.with(holder.itemView.context)
            .load(detil[position].image_url)
            .error(ColorDrawable(Color.DKGRAY))
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imageUrl)

        holder.itemView.setOnClickListener{ listener?.invoke(detil[position])}

    }

    override fun getItemCount(): Int = detil.size

    fun setTomato(list : List<DetailTomato>) {
        this.detil = list
        Log.i("Size List Plant", detil.size.toString())
        notifyDataSetChanged()
    }
}