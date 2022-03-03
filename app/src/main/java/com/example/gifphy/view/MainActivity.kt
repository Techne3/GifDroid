package com.example.gifphy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gifphy.R

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}