package com.example.hetrogenousrecyclerview

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pagination: AppCompatActivity() {

    private val listViewType = mutableListOf<Int>()

    private var startPage = 1
    private var isLoading = false
    private val limit = 4

    private val progressBar: ProgressBar? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagination)

        recyclerView = findViewById(R.id.recyclerViewPagination)
        layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager

        recyclerAdapter = RecyclerAdapter(this, listViewType = listViewType)
        recyclerView.adapter = recyclerAdapter

        getPage()

        Handler().post({checkForPagination()})

        /**
         * To enable Pagination, we must detect the user reaching the end of the list (RecyclerView).
         * PaginationScrollListener allows us to do so.
         */

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                // dy means vertical scroll position
                if (dy>0){
                    Log.e("value of dy" , "dy " + dy )
                    checkForPagination()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }


    private fun checkForPagination() {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        Log.e("Scrolled View variables", "visibleItemCount" + visibleItemCount)
        Log.e("Scrolled View variables", "totalItemCount" + totalItemCount)
        Log.e("Scrolled View variables", "firstVisibleItemPosition" + firstVisibleItemPosition)

        if(!isLoading){
            if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount){
                startPage++
                progressBar?.visibility = View.VISIBLE
                isLoading = true
                Handler().postDelayed({getPage()},2000)
            }
        }
    }


    private fun getPage() {
        addDataInList()

        progressBar?.visibility = View.GONE
        recyclerAdapter.notifyDataSetChanged()
        isLoading = false

    }


    private fun addDataInList() {
        val start = (startPage - 1)*limit
        val end = (startPage)*limit

        for(i in start..end){
            when{
                i%2 == 0 ->{
                     listViewType.add(RecyclerAdapter.TEXT_VIEW)
                 }
                i%3 == 0 ->{
                    listViewType.add(RecyclerAdapter.IMAGE_TEXT_VIEW)
                }
                else ->{
                    listViewType.add(RecyclerAdapter.IMAGE_VIEW)
                }
            }
        }

    }

}