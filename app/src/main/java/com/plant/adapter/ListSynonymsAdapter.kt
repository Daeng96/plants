package com.plant.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.plant.R

class ListSynonymsAdapter internal constructor() : RecyclerView.Adapter<ListSynonymsAdapter.ListViewHolder>() {

    private var detil = emptyList<String>()

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tomato: TextView = view.findViewById(R.id.synonym_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_synonym, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tomato.text = detil[position]
    }

    override fun getItemCount(): Int = detil.size

    fun setTomato(list : List<String>) {
        this.detil = list
        Log.d("Size", detil.size.toString())
        notifyDataSetChanged()
    }
}