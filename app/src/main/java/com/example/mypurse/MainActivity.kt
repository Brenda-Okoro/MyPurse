package com.example.mypurse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mypurse.databinding.ActivityMainBinding
import com.example.mypurse.ui.CustomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            CustomDialog().show(supportFragmentManager, "CustomFragment")
        }
    }
}