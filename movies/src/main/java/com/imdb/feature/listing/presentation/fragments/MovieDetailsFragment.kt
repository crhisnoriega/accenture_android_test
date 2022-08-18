package com.imdb.feature.listing.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imdb.core.base.BaseFragment
import com.imdb.core.util.argument
import com.imdb.feature.listing.BR
import com.imdb.feature.listing.databinding.FragmentMovieDetailBinding
import com.imdb.feature.listing.presentation.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: MovieDetailsViewModel by viewModel()

    private val titleId by argument<String>(TITLE_ID_ARG)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        configureObservables()

        viewModel.fetMovieDetails(titleId)
    }

    private fun configureObservables() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding.movieDetail = it
            binding.viewModel = viewModel
        }
    }


    companion object {

        const val TITLE_ID_ARG = "TITLE_ID_ARG"

        fun newInstance(titleId: String) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(TITLE_ID_ARG, titleId)
            }
        }
    }

}