package com.gustavo.pagedlistappandroid.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustavo.pagedlistappandroid.R
import com.gustavo.pagedlistappandroid.view.adapter.ComicAdapter
import com.gustavo.pagedlistappandroid.view.viewmodel.ComicViewModel
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StatusBarUtil.setTransparent(this)
        StatusBarUtil.setDarkMode(this)

        recycler_comics.layoutManager = LinearLayoutManager(this)
        recycler_comics.setHasFixedSize(true)

        val comicViewModel = ViewModelProviders.of(this).get(ComicViewModel::class.java)

        //creating the Adapter
        val adapter = ComicAdapter(this);

        comicViewModel.resultPagedList.observe(this, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })
        recycler_comics.adapter = adapter
    }
}
