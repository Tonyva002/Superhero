package com.pangea.superhero.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pangea.superhero.domain.model.SuperHero

class SuperHeroDiffCallback: DiffUtil.ItemCallback<SuperHero>() {
    override fun areItemsTheSame(
        oldItem: SuperHero,
        newItem: SuperHero
    ): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SuperHero,
        newItem: SuperHero
    ): Boolean {
        return oldItem == newItem
    }
}