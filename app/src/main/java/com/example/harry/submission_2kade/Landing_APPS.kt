package com.example.harry.submission_2kade

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.harry.submission_2kade.Adapter.FragmentAdapter
import kotlinx.android.synthetic.main.activity_landing__apps.*

class Landing_APPS : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing__apps)

        toolbar.title = "KADE 2"
        toolbar.setTitleTextColor(Color.WHITE)
        view_pager.adapter = FragmentAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
    }
}
