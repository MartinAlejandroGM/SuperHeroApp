package com.example.superheroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroapp.R
import com.example.superheroapp.adapters.SuperHeroRVAdapter
import com.example.superheroapp.decoration.HeroDecoration
import com.example.superheroapp.extensions.onLoadMore
import com.example.superheroapp.viewmodel.SuperViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SuperViewModel
    private lateinit var heroAdapter: SuperHeroRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = SuperViewModel(application)
        observeHeros()
        initRecyclerView()
        fetchFirstPage()

        main_activity_toolbar.setTitle(R.string.tittle)
        setSupportActionBar(main_activity_toolbar)
    }

    private fun initRecyclerView() {
        heroRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            onLoadMore {
                fetchNextPage()
            }
            addItemDecoration(HeroDecoration())
            heroAdapter = SuperHeroRVAdapter()
            adapter = heroAdapter
            heroAdapter.onSuperHeroClickListener = {
                val superHeroIntent = SuperHeroDetails.getIntent(this@MainActivity, it)
                startActivity(superHeroIntent)
                Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeHeros() {
        viewModel.superHeroLiveData.observe(this, Observer {
            heroAdapter.submitList(it)
        })
    }

    private fun fetchFirstPage(){
        viewModel.fetchFirstPage()
    }

    private fun fetchNextPage(){
        viewModel.fetchNextPage()
    }
}