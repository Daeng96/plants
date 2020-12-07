package com.plant.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.plant.R
import com.plant.adapter.ViewPagerAdapter
import com.plant.databinding.HomeViewPagerBinding

class HomeViewPager : Fragment() {

    private lateinit var binding : HomeViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            0 -> R.drawable.ic_baseline_eco_24
            1 -> R.drawable.ic_baseline_local_florist_24
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> getString(R.string.tomato_title_tab)
            1 -> getString(R.string.palm_title_tab)
            else -> null
        }
    }

}