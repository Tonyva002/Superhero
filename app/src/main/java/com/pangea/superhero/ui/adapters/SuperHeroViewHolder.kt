package com.pangea.superhero.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.pangea.superhero.databinding.ItemSuperheroBinding
import com.pangea.superhero.domain.model.SuperHero
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(private val binding: ItemSuperheroBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(superhero: SuperHero, onItemSelected: (String) -> Unit){
        binding.tvSuperheroName.text = superhero.name
        Picasso.get()
            .load(superhero.imageUrl)
            .into(binding.imgSuperhero)

        binding.root.setOnClickListener {
            onItemSelected(superhero.id)
        }
    }
}