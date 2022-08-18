package com.imdb.feature.listing.presentation

import android.os.Bundle
import com.imdb.core.base.BaseActivity
import com.imdb.feature.listing.R
import com.imdb.feature.listing.di.HomeModule
import com.imdb.feature.listing.presentation.fragments.MovieListFragment
import org.koin.core.module.Module

class HomeActivity : BaseActivity() {
    override val modules: List<Module> = listOf(HomeModule.modules)
    override val contentView: Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureInitialFragment()
    }

    private fun configureInitialFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieListFragment.newInstance())
            .commit()
    }
}