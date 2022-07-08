package com.example.nicklefox.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicklefox.FunctionUtil
import com.example.nicklefox.databinding.ActivityMainBinding
import com.example.nicklefox.view_model.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), YoutubeAdapter.ClickContract {

    lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var itemCount = 20
    private val adapter = YoutubeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        if (FunctionUtil.isInternetOn(this)) {
            apiCall()
            setAdapter()
        } else {
            Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_SHORT).show()
        }

    }

    private fun setAdapter() {

        val layoutManager = LinearLayoutManager(this)
        activityMainBinding.recyclerView.layoutManager = layoutManager
        activityMainBinding.recyclerView.itemAnimator = DefaultItemAnimator()
        activityMainBinding.recyclerView.adapter = adapter

        activityMainBinding.nestedScorllView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                activityMainBinding.progressBar.visibility = View.VISIBLE
                itemCount += 20
                viewModel.callParseDataCall(itemCount)
            }
        })

    }

    private fun apiCall() {
        viewModel.callParseDataCall(itemCount)
        viewModel.listLiveData.observe(this) {
            activityMainBinding.progressBar.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                Snackbar
                    .make(
                        activityMainBinding.cordinatelayout,
                        "Error please try again",
                        Snackbar.LENGTH_LONG
                    ).show()
            } else {
                adapter.submitList(it)
            }
        }
    }

    override fun itemClick(adapterPosition: Int, videoID: String?) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoID"))
        val intentBrowser =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$videoID"))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }
    }
}