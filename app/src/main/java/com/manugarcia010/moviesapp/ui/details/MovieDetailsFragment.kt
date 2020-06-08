package com.manugarcia010.moviesapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.manugarcia010.moviesapp.R
import com.manugarcia010.moviesapp.databinding.MovieDetailsFragmentBinding
import com.manugarcia010.moviesapp.databinding.MoviesFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: MovieDetailsFragmentBinding

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.movie_details_fragment, container, false)
        binding = MovieDetailsFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.start(args.movieId)

        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
