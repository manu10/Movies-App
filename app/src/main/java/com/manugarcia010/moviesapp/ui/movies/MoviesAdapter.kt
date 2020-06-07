package com.manugarcia010.moviesapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manugarcia010.moviesapp.databinding.MovieItemBinding

class MoviesAdapter(private val viewModel: MoviesViewModel) :
    ListAdapter<MovieUI, MoviesAdapter.ViewHolder>(MoviesDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MoviesViewModel, item: MovieUI) {
            binding.movie = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}
/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class MoviesDiffCallback : DiffUtil.ItemCallback<MovieUI>() {
    override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
        return oldItem == newItem
    }
}
