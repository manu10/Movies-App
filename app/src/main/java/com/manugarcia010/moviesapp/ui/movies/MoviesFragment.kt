package com.manugarcia010.moviesapp.ui.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.manugarcia010.moviesapp.R
import com.manugarcia010.moviesapp.databinding.MoviesFragmentBinding
import com.manugarcia010.moviesapp.domain.usecase.MoviesOrderCriteria
import com.manugarcia010.moviesapp.ui.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: MoviesFragmentBinding
    private lateinit var moviesAdapter: MoviesAdapter

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
//        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE)
//        activity?.componentName
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                viewModel.search(s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener { false }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_filter -> {
                showOrderCriteriaPopUpMenu()
                true
            }

            else -> false
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
