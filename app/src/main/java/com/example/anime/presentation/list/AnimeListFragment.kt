package com.example.anime.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.R
import com.example.anime.presentation.Singletons
import com.example.anime.presentation.api.AnimeApi
import com.example.anime.presentation.api.AnimeListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AnimeListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    private val adapter = AnimeAdapter(listOf(), ::onClickedAnime)
    private val viewModel: AnimeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.anime_recylerview)
        loader = view.findViewById(R.id.anime_loader)
        textViewError = view.findViewById(R.id.anime_error)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@AnimeListFragment.adapter
        }
        viewModel.animeList.observe(viewLifecycleOwner, Observer { animeModel ->
            loader.isVisible = animeModel is AnimeLoader
            textViewError.isVisible = animeModel is AnimeError
            if (animeModel is AnimeSuccess) {
                adapter.updateList(animeModel.animeList)
            }
        })
    }
    private fun onClickedAnime(id: Int) {
        val anime: Anime = adapter.dataSet[id]
        findNavController().navigate(R.id.navigateToAnimeDetailFragment, bundleOf(
            "animeId" to (id + 1),
            "animeSynopsis" to (anime.attributes.synopsis),
            "animeTitles" to (anime.attributes.canonicalTitle)
        ))
    }
}