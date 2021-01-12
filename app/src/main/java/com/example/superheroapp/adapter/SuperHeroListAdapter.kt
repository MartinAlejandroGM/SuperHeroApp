package com.example.superheroapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.models.SuperHeroResponse
import kotlinx.android.synthetic.main.super_hero_list_view.view.*
import com.squareup.picasso.Picasso

class SuperHeroListAdapter : RecyclerView.Adapter<SuperHeroListAdapter.SuperHeroViewHolder>() {
    private var superHeroList = ArrayList<SuperHeroResponse>()
    var onSuperHeroClickListener: ((SuperHeroResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.super_hero_list_view, parent, false)
        )
    }

    fun clear(){
        superHeroList.clear()
        notifyDataSetChanged()
    }

    fun isEmpty() = superHeroList.isEmpty()

    fun submitList(heroList: List<SuperHeroResponse>, shouldClear: Boolean = false) {
        if (shouldClear) {
            superHeroList.clear()
        }
        superHeroList.addAll(heroList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.generationsBind(superHeroList[position])
    }

    override fun getItemCount() = superHeroList.size

    inner class SuperHeroViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        fun generationsBind(superHero: SuperHeroResponse) = itemView.run {
            heroName.text = superHero.name
            Picasso.get()
                .load(superHero.image.url)
                .error(R.drawable.ic_launcher_background)
                .into(heroImage)
            setOnClickListener {
                onSuperHeroClickListener?.invoke(superHero)
            }
        }
    }

}