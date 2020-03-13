package com.example.testcodex.injection.component

import com.example.testcodex.injection.module.NetworkModule
import com.example.testcodex.ui.activity.detailstories.DetailStoriesViewModel
import com.example.testcodex.ui.activity.topstories.TopStoriesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(topStoriesViewModel: TopStoriesViewModel)
    fun inject(detailStoriesViewModel: DetailStoriesViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}