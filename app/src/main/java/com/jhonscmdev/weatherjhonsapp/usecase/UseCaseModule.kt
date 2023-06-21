package com.jhonscmdev.weatherjhonsapp.usecase

import com.jhonscmdev.weatherjhonsapp.data.MockyRepository
import com.jhonscmdev.weatherjhonsapp.usecase.getAllMockyUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun getAllMockyUseCaseProvider(mockyRepository: MockyRepository) = getAllMockyUseCase(mockyRepository)
}