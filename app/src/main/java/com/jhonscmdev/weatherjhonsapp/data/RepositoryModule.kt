package com.jhonscmdev.weatherjhonsapp.data

import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun mockyRespositoryProvider(
        remoteMockyDataSource: RemoteMockyDataSource,
    ) = MockyRepository(remoteMockyDataSource)
}