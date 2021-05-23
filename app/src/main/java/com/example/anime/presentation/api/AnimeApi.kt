package com.example.anime.presentation.api

import com.example.anime.presentation.detail.AnimeDetailFragment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {
    @GET("anime")
    fun getAnimeList(): Call<AnimeListResponse>

    @GET("anime/{id}/genres")
    fun getAnimeDetail(@Path("id")id: Int): Call<AnimeDetailResponse>
}