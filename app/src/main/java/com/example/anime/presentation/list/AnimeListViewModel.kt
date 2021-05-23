package com.example.anime.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anime.presentation.Singletons
import com.example.anime.presentation.api.AnimeListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeListViewModel : ViewModel(){
    val animeList : MutableLiveData<AnimeModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        animeList.value = AnimeLoader
        Singletons.animeApi.getAnimeList().enqueue(object: Callback<AnimeListResponse> {
            override fun onFailure(call: Call<AnimeListResponse>, t: Throwable) {
                animeList.value = AnimeError
            }
            override fun onResponse(
                call: Call<AnimeListResponse>,
                response: Response<AnimeListResponse>
            ) {
                if(response.isSuccessful && response.body() != null){
                    val animeResponse : AnimeListResponse = response.body()!!
                    animeList.value = AnimeSuccess(animeResponse.data)
                } else {
                    animeList.value = AnimeError
                }
            }
        })
    }
}