package com.example.hetrogenousrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView  = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listViewType = mutableListOf(
            RecyclerAdapter.TEXT_VIEW,
            RecyclerAdapter.IMAGE_VIEW,
            RecyclerAdapter.IMAGE_TEXT_VIEW,
            RecyclerAdapter.IMAGE_VIEW,
                RecyclerAdapter.TEXT_VIEW,
            RecyclerAdapter.IMAGE_VIEW,
            RecyclerAdapter.IMAGE_TEXT_VIEW
        )

        val adapter = RecyclerAdapter(this,listViewType = listViewType)
        recyclerView.adapter = adapter
    }
}