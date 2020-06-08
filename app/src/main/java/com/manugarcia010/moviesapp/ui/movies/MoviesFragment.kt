package com.manugarcia010.moviesapp.ui.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.manugarcia010.moviesapp.R
import com.manugarcia010.moviesapp.databinding.MoviesFragmentBinding
import com.manugarcia010.moviesapp.domain.usecase.MoviesOrderCriteria
import com.manugarcia010.moviesapp.ui.EventObserver
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: MoviesFragmentBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.movies_fragment, container, false)
        binding = MoviesFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        binding.lifecycleOwner = this.viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movies_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            /*R.id.menu_search -> { todo
                true
            }*/
            R.id.menu_filter -> {
                showOrderCriteriaPopUpMenu()
                true
            }

            else -> false
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        setupNavigation()
        viewModel.loadMovies()
    }

    private fun setupNavigation() {
        viewModel.openMovieDetailsEvent.observe(this.viewLifecycleOwner, EventObserver {
            openMovieDetails(it)
        })
    }

    private fun showOrderCriteriaPopUpMenu() {
        val view = activity?.findViewById<View>(R.id.menu_filter) ?: return
        PopupMenu(requireContext(), view).run {
            menuInflater.inflate(R.menu.filter_movies, menu)

            setOnMenuItemClickListener {
                viewModel.setOrderCriteria(
                    when (it.itemId) {
                        R.id.popular -> MoviesOrderCriteria.POPULAR
                        R.id.topRated -> MoviesOrderCriteria.TOP_RATED
                        else -> null
                    }
                )
                viewModel.loadMovies()
                true
            }
            show()
        }
    }
    private fun openMovieDetails(movieId: Int) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewmodel
        if (viewModel != null) {
            moviesAdapter = MoviesAdapter(viewModel)
            binding.movieList.adapter = moviesAdapter
        }
    }

}
