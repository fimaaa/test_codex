package com.example.testcodex.base

import androidx.lifecycle.ViewModel
import com.example.testcodex.injection.component.DaggerViewModelInjector
import com.example.testcodex.injection.component.ViewModelInjector
import com.example.testcodex.injection.module.NetworkModule
import com.example.testcodex.ui.activity.topstories.TopStoriesViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is TopStoriesViewModel -> injector.inject(this)
//            is SearchMovieViewModel -> injector.inject(this)
        }
    }
}