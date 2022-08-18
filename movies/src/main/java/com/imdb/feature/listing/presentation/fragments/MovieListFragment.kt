package com.imdb.feature.listing.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imdb.core.base.BaseFragment
import com.imdb.feature.listing.BR
import com.imdb.feature.listing.databinding.FragmentMovieListBinding
import com.imdb.feature.listing.presentation.HomeActivity
import com.imdb.feature.listing.presentation.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieListBinding

    private val viewModel: MovieListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        configureObservables()
        configureComponent()

        viewModel.fetchMovies()
    }

    private fun configureObservables() {
        viewModel.movies.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel
        }
    }

    private fun configureComponent() {
        binding.root.rvMovies.onClick = { movie ->
            (requireActivity() as? HomeActivity)?.let {
                it.showDetailMovie(movie.id)
            }
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }

}