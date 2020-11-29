package com.plant.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plant.R
import com.plant.adapter.SpeciesAdapter
import com.plant.pojo.Tomato
import com.plant.utils.Utils
import com.plant.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_tomato_species.*


private const val ARG_PARAM1 = "param1"

private const val ASSET_NAME_TOMATO = "tomato.json"
private const val ASSET_NAME_PALM = "palm.json"

class TomatoSpeciesFragment : Fragment() {

    private var param1: Int? = null

    private lateinit var viewModel: MainViewModel
    private lateinit var dataPlants: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            param1 = bundle.getInt(ARG_PARAM1, 0)
            Log.d("Arguments Parameter", param1.toString())

            dataPlants = if (param1 == 0) {
                context?.let { context -> Utils.getJsonData(context, ASSET_NAME_TOMATO) }
                        .toString()
            } else {
                context?.let { context -> Utils.getJsonData(context, ASSET_NAME_PALM) }
                        .toString()
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = SpeciesAdapter()
        val plantType = object : TypeToken<Tomato>() {}.type
        val plantList: Tomato = Gson().fromJson(dataPlants, plantType)

        viewModel.setTomato(plantList)
        viewModel.listPlants.observe(viewLifecycleOwner, { tomato ->
            tomato.data?.let {
                adapter.setTomato(it)
            }
        })


        rv_tomato.apply {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
            this.setHasFixedSize(true)
        }

        adapter.click { dTomato ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, dTomato)
            startActivity(intent)
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tomato_species, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(index: Int) =
                TomatoSpeciesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, index)
                    }
                }
    }
}