package com.example.superheroapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.models.SuperHeroResponse
import kotlinx.android.synthetic.main.fragment_appearance.view.*
import kotlinx.android.synthetic.main.fragment_biography.view.*
import kotlinx.android.synthetic.main.fragment_connections.view.*
import kotlinx.android.synthetic.main.fragment_power_stats.view.*
import kotlinx.android.synthetic.main.fragment_work.view.*

class SuperHeroDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var superHero: SuperHeroResponse
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HERO_POWER_STATS -> {
                PowerStatsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.fragment_power_stats, parent, false)
                )
            }
            HERO_BIOGRAPHY -> {
                BiographyViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.fragment_biography, parent, false)
                )
            }
            HERO_APPEARANCE -> {
                AppearanceViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.fragment_appearance, parent, false)
                )
            }
            HERO_WORK -> {
                WorkViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.fragment_work, parent, false)
                )
            }
            else -> {
                ConnectionsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.fragment_connections, parent, false)
                )
            }
        }
    }

    fun submitHero(hero: SuperHeroResponse){
        superHero = hero
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PowerStatsViewHolder -> {
                holder.bindHeroPowerStats(superHero)
            }
            is AppearanceViewHolder -> {
                holder.bindHeroAppearance(superHero)
            }
            is BiographyViewHolder -> {
                holder.bindHeroBiography(superHero)
            }
            is ConnectionsViewHolder -> {
                holder.bindHeroConnections(superHero)
            }
            is WorkViewHolder -> {
                holder.bindHeroWork(superHero)
            }
        }
    }

    override fun getItemCount() = 5

    override fun getItemViewType(position: Int) = position

    inner class PowerStatsViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        fun bindHeroPowerStats(hero: SuperHeroResponse) = itemView.run {
            intelligence.text = hero.powerStats.intelligence
            strength.text = hero.powerStats.strength
            speed.text = hero.powerStats.speed
            durability.text = hero.powerStats.durability
            power.text = hero.powerStats.power
            combat.text = hero.powerStats.combat
        }
    }

    inner class AppearanceViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        fun bindHeroAppearance(hero: SuperHeroResponse) = itemView.run {
            gender.text = hero.appearance.gender
            heroHeight.text = hero.appearance.height.toString()
            heroWeight.text = hero.appearance.weight.toString()
            eyeColor.text = hero.appearance.eyeColor
            hairColor.text = hero.appearance.hairColor
        }
    }

    inner class BiographyViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        fun bindHeroBiography(hero: SuperHeroResponse) = itemView.run {
            alterEgos.text = hero.biography.alterEgos
            aliases.text = hero.biography.aliases.toString()
            placeOfBirth.text = hero.biography.placeOfBirth
            firstAppearance.text = hero.biography.firstAppearance
            publisher.text = hero.biography.publisher
            alignment.text = hero.biography.alignment
        }
    }

    inner class ConnectionsViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        fun bindHeroConnections(hero: SuperHeroResponse) = itemView.run {
            groupAffiliations.text = hero.connections.groupAffiliation
            relatives.text = hero.connections.relatives
        }
    }

    inner class WorkViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        fun bindHeroWork(hero: SuperHeroResponse) = itemView.run {
            occupations.text = hero.work.occupation
            base.text = hero.work.base
        }
    }

    companion object{
        private const val HERO_POWER_STATS = 0
        private const val HERO_BIOGRAPHY = 1
        private const val HERO_APPEARANCE = 2
        private const val HERO_WORK = 3
    }

}