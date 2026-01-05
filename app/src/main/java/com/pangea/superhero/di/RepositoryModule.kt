package com.pangea.superhero.di

import com.pangea.superhero.data.repository.SuperHeroRepositoryImpl
import com.pangea.superhero.domain.repository.SuperHeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindSuperHeroRepository(
        impl: SuperHeroRepositoryImpl
    ): SuperHeroRepository
}