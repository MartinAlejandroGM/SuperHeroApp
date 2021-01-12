package com.example.superheroapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroapp.R
import com.example.superheroapp.adapter.SuperHeroDetailsAdapter
import com.example.superheroapp.models.SuperHeroResponse
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_super_hero_details.*

class SuperHeroDetailsActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: SuperHeroDetailsAdapter
    private lateinit var superHero: SuperHeroResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_hero_details)

        setSupportActionBar(details_activity_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        details_activity_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        superHero = intent.getParcelableExtra(ARG_HERO_KEY) as SuperHeroResponse

        superHero.let {
            Picasso.get()
                .load(it.image.url)
                .error(R.drawable.ic_launcher_background)
                .into(superImage)
            superName.text = it.name
            superFullName.text = it.biography.fullName
            superRace.text = it.appearance.race
        }
        initViewPager()
        observeHero()
        attachPager()
    }

    private fun attachPager(){
        TabLayoutMediator(tab_layout, pager) { tab, position ->
            when (position) {
                0->{
                    tab.setText(R.string.powerstats)
                }
                1->{
                    tab.setText(R.string.biography)
                }
                2->{
                    tab.setText(R.string.appearance)
                }
                3->{
                    tab.setText(R.string.work)
                }
                4->{
                    tab.setText(R.string.connections)
                }
            }
        }.attach()
    }

    private fun initViewPager() {
        pager.apply {
            viewPagerAdapter = SuperHeroDetailsAdapter()
            adapter = viewPagerAdapter
        }
    }

    private fun observeHero() {
        viewPagerAdapter.submitHero(superHero)
    }

    companion object {
        private const val NAMESPACE = "com.example.pokedix.ui"
        private const val ARG_HERO_KEY = "${NAMESPACE}.game"

        fun getIntent(context: Context, superHero: SuperHeroResponse): Intent {
            return Intent(context, SuperHeroDetailsActivity::class.java).apply {
                putExtra(ARG_HERO_KEY, superHero)
            }

        }
    }
}