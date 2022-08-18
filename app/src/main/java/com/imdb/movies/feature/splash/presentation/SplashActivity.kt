package com.imdb.movies.feature.splash.presentation

import android.content.Intent
import android.os.Bundle
import com.imdb.movies.R
import com.imdb.core.base.BaseActivity
import com.imdb.feature.listing.presentation.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.module.Module

class SplashActivity : BaseActivity() {
    override val modules: List<Module> = listOf()
    override val contentView: Int = R.layout.activity_splash

    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityScope.launch {
            delay(3000)

            var intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}