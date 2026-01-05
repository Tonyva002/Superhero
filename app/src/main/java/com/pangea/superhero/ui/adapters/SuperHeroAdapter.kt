package com.pangea.superhero.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pangea.superhero.databinding.ItemSuperheroBinding

import com.pangea.superhero.domain.model.SuperHero

class SuperHeroAdapter(private val onItemSelected: (String) -> Unit
) : ListAdapter<SuperHero, SuperHeroViewHolder>(
    SuperHeroDiffCallback()
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperHeroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperHeroViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SuperHeroViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), onItemSelected)
    }
}