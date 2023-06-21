package com.jhonscmdev.weatherjhonsapp.di

import com.jhonscmdev.weatherjhonsapp.presentation.HomeViewModel
import com.jhonscmdev.weatherjhonsapp.usecase.getAllMockyUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class HomeModule {
    @Provides
    fun MockyViewModelProvider(
        getAllMockyUseCase: getAllMockyUseCase
    ) = HomeViewModel(getAllMockyUseCase)
}
@Subcomponent(modules = [(HomeModule::class)])
interface HomeMockyComponent {
    val homeViewModel: HomeViewModel
}