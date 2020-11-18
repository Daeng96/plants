package com.plant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plant.view.HomeViewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_main, HomeViewPager())
            .commit()
    }
}