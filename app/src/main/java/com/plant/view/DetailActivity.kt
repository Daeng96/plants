package com.plant.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plant.R
import com.plant.adapter.ListSynonymsAdapter
import com.plant.pojo.DetailTomato
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var dTomato: DetailTomato? = null
    private lateinit var adapter: ListSynonymsAdapter

    companion object {
        const val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        val listView: RecyclerView = findViewById(R.id.list_synonyms)

        dTomato = intent.getParcelableExtra(EXTRA_DATA)

        Glide.with(this)
                .load(dTomato?.image_url)
                .placeholder(ColorDrawable(Color.GREEN))
                .into(detail_image)

        init(dTomato?.common_name)

        plant_family.text = dTomato?.family
        author.text = dTomato?.author
        plant_scientific_name.text = dTomato?.scientific_name
        plant_family_common.text = dTomato?.family_common_name


        val synonymList = (dTomato?.synonyms)

        Log.d("size synonim", synonymList?.size.toString())

        adapter = ListSynonymsAdapter()
        adapt(listView, adapter)
        dTomato?.synonyms?.let { adapter.setTomato(it) }

    }

    private fun adapt(listView : RecyclerView, adapter: ListSynonymsAdapter) {
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = adapter
        listView.setHasFixedSize(true)
        listView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }

    private fun init(myTitle: String?) {
        supportActionBar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setHomeButtonEnabled(true)
            this.title = myTitle
        }
    }

    //menampilkan dan menambahka onklik pada navigasi
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}