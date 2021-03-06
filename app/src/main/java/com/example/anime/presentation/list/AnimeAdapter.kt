package com.example.anime.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anime.R

class AnimeAdapter(var dataSet: List<Anime>, var listener: ((Int) -> Unit)? = null) :
    RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.anime_name)
            imageView = view.findViewById(R.id.anime_img)
        }
    }

    fun updateList(list: List<Anime>){
        dataSet = list
        notifyDataSetChanged()
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.anime_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val anime: Anime = dataSet[position]
        viewHolder.textView.text = anime.attributes.canonicalTitle
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
        Glide
            .with(viewHolder.itemView.context)
            .load(anime.attributes.posterImage.tiny)
            .centerCrop()
            .into(viewHolder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
