package com.pangea.superhero.data.remote
import com.pangea.superhero.data.dto.SuperHeroDataResponse
import com.pangea.superhero.data.dto.SuperHeroDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/59a124af30b939608591954d541135aa/search/{name}")

    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    @GET("api/59a124af30b939608591954d541135aa/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>
}