package com.example.superheroapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.models.SuperHeroResponse
import kotlinx.android.synthetic.main.super_hero_list_view.view.*
import com.squareup.picasso.Picasso

class SuperHeroRVAdapter : RecyclerView.Adapter<SuperHeroRVAdapter.SuperHeroViewHolder>() {
    private var superHeroList = ArrayList<SuperHeroResponse>()
    var onSuperHeroClickListener: ((SuperHeroResponse) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.super_hero_list_view, parent, false)
        )
    }

    fun submitList(heroList: List<SuperHeroResponse>, shouldClear: Boolean = false) {
        if (shouldClear) {
            superHeroList.clear()
        }
        val size = superHeroList.size
        superHeroList.addAll(heroList)
        val newSize = superHeroList.size
        notifyDataSetChanged()
        notifyItemRangeChanged(size, newSize)
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