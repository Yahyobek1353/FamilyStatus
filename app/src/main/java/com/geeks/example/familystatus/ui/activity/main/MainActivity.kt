package com.geeks.example.familystatus.ui.activity.main

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.geeks.example.familystatus.R
import com.geeks.example.familystatus.databinding.ActivityMainBinding
import com.geeks.example.familystatus.other.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = ViewPagerAdapter(supportFragmentManager , lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager.adapter = adapter
        mediator()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN   )

    }

    private fun mediator() {
        TabLayoutMediator(binding.tabLayout , binding.viewPager){
            tab,pos ->
            run {
                when (pos) {
                    0 -> {
                        tab.text = "Расписание"
                    }

                    1 -> {
                        tab.text = "Биюджет"
                    }

                    2 -> {
                        tab.text = "Счёт"
                    }
                }
            }
        }.attach()
    }
}