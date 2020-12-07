package com.plant.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plant.R
import com.plant.adapter.ListSynonymsAdapter
import com.plant.databinding.ActivityDetailBinding
import com.plant.pojo.DetailSpecies


class DetailActivity : AppCompatActivity() {

    private var dSpecies: DetailSpecies? = null
    private lateinit var adapter: ListSynonymsAdapter

//    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)

//        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val listView: RecyclerView = findViewById(R.id.list_synonyms)

        dSpecies = intent.getParcelableExtra(EXTRA_DATA)

        Glide.with(this)
                .load(dSpecies?.image_url)
                .placeholder(R.drawable.ic_background_detail_img)
                .into(binding.detailImage)

        init(dSpecies?.common_name)

        binding.plantFamily.text = dSpecies?.family
        binding.author.text = dSpecies?.author
        binding.plantScientificName.text = dSpecies?.scientific_name
        binding.plantFamilyCommon.text = dSpecies?.family_common_name

        val synonymList = (dSpecies?.synonyms)

        Log.d("size synonym", synonymList?.size.toString())

        adapter = ListSynonymsAdapter()
        adapt(listView, adapter)
        dSpecies?.synonyms?.let { adapter.setTomato(it) }

        binding.toolbarDetail.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.ic_share -> {
                    val txt = "${dSpecies?.common_name} - Synonyms : ${dSpecies?.synonyms}"
                    val shareIntent = ShareCompat.IntentBuilder.from(this)
                            .setText(txt)
                            .setType("text/plain")
                            .createChooserIntent()
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                    startActivity(shareIntent)
                    true

                }
                else -> false
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        return true
    }


    private fun adapt(listView: RecyclerView, adapter: ListSynonymsAdapter) {
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}