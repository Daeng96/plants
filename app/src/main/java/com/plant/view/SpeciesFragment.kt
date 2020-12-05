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
import com.plant.adapter.SpeciesAdapter
import com.plant.databinding.FragmentSpeciesBinding
import com.plant.pojo.Species
import com.plant.utils.Utils
import com.plant.viewmodel.MainViewModel


private const val ARG_PARAM1 = "param1"
private const val ASSET_NAME_SPECIES = "species.json"
private const val ASSET_NAME_PLANTS = "plants.json"

class SpeciesFragment : Fragment() {

    private var param1: Int? = null
    private lateinit var binding: FragmentSpeciesBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var dataPlants: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            param1 = bundle.getInt(ARG_PARAM1, 0)
            Log.d("Arguments Parameter", param1.toString())

            dataPlants = if (param1 == 0) {
                context?.let { context -> Utils.getJsonData(context, ASSET_NAME_PLANTS) }
                        .toString()
            } else {
                context?.let { context -> Utils.getJsonData(context, ASSET_NAME_SPECIES) }
                        .toString()
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = SpeciesAdapter()
        val plantType = object : TypeToken<Species>() {}.type
        val plantList: Species = Gson().fromJson(dataPlants, plantType)

        viewModel.setSpecies(plantList)
        viewModel.listPlants.observe(viewLifecycleOwner, { Species ->
            Species.data?.let {
                adapter.setSpecies(it)
            }
        })


        binding.rvSpecies.apply {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
            this.setHasFixedSize(true)
        }

        adapter.click { dSpecies ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, dSpecies)
            startActivity(intent)
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpeciesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(index: Int) =
                SpeciesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, index)
                    }
                }
    }
}