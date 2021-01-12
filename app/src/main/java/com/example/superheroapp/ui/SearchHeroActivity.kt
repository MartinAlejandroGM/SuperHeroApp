package com.example.superheroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroapp.R
import com.example.superheroapp.adapters.SuperHeroRVAdapter
import com.example.superheroapp.decoration.HeroDecoration
import com.example.superheroapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.heroRecyclerView
import kotlinx.android.synthetic.main.activity_search_hero.*
import kotlinx.android.synthetic.main.activity_super_hero_details.*
import kotlinx.android.synthetic.main.activity_super_hero_details.details_activity_toolbar

class SearchHeroActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var heroAdapter: SuperHeroRVAdapter
    private var isEnterPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_hero)

        setSupportActionBar(details_activity_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        details_activity_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = SearchViewModel(application)
        observeHeroes()
        initRecyclerView()
        searchBox.setOnKeyListener { _, keyCode, event ->
            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val userSearchTerm = searchBox.text.toString()
                viewModel.fetchSearch(userSearchTerm)
                isEnterPressed = true
            }
            false
        }

        searchBox.doOnTextChanged { text, _, _, _ ->
            val heroSearched = text.toString()
            viewModel.fetchSearch(heroSearched)
            isEnterPressed = false
        }
    }

    private fun initRecyclerView() {
        heroRecyclerView.apply {
            layoutManager = GridLayoutManager(this@SearchHeroActivity, 2)
            addItemDecoration(HeroDecoration())
            heroAdapter = SuperHeroRVAdapter()
            adapter = heroAdapter
            heroAdapter.onSuperHeroClickListener = {
                val superHeroIntent = SuperHeroDetailsActivity.getIntent(this@SearchHeroActivity, it)
                startActivity(superHeroIntent)
                Toast.makeText(this@SearchHeroActivity, it.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeHeroes() {
        viewModel.searchLiveData.observe(this, Observer {
            when {
                it == null ->{
                    heroAdapter.clear()
                    Toast.makeText(this, "No Heroes found!", Toast.LENGTH_SHORT).show()
                }
                it.size == 1 && isEnterPressed ->{
                    val superHeroIntent = SuperHeroDetailsActivity.getIntent(this, it.first())
                    startActivity(superHeroIntent)
                }
                else ->{
                    heroAdapter.submitList(it, true)
                }
            }
        })
    }
}