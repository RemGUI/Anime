package com.example.anime.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.anime.R
import com.example.anime.presentation.Singletons
import com.example.anime.presentation.api.AnimeDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimeDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewName2: TextView
    private lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName = view.findViewById(R.id.anime_detail_name)
        textViewName2 = view.findViewById(R.id.anime2_detail_name)
        imageView = view.findViewById(R.id.anime_detail_img)
        callApi()

    }

    private fun callApi() {
        val id: Int = arguments?.getInt("animeId") ?: -1
        val synopsis: String = arguments?.getString("animeSynopsis") ?: ""
        val title: String = arguments?.getString("animeTitles") ?: ""
        Singletons.animeApi.getAnimeDetail(id).enqueue(object : Callback<AnimeDetailResponse>{
            override fun onFailure(call: Call<AnimeDetailResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<AnimeDetailResponse>,
                response: Response<AnimeDetailResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
//                    textViewName.text = response.body()!!.meta.count
                    textViewName2.text = title
                    textViewName.text = synopsis
                }
            }
        })
    }
}