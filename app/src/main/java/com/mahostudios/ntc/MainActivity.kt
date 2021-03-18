package com.mahostudios.ntc


import android.annotation.SuppressLint
import android.app.ActivityManager
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mahostudios.ntc.fragments.MainFragment
import com.mahostudios.ntc.fragments.UsageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navCont = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navCont)

        bottomNavigationView.selectedItemId = R.id.table


    }



}