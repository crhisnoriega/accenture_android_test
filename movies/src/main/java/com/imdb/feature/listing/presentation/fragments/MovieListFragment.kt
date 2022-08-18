package com.imdb.feature.listing.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imdb.core.base.BaseFragment
import com.imdb.feature.listing.BR
import com.imdb.feature.listing.databinding.FragmentMovieListBinding
import com.imdb.feature.listing.presentation.viewmodel.MovieListViewModel
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

        viewModel.fetchMovies()
    }

    private fun configureObservables() {
        viewModel.movies.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }

}