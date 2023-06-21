package com.jhonscmdev.weatherjhonsapp.framework.request

import com.jhonscmdev.weatherjhonsapp.data.RemoteMockyDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    fun infrequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = ServerRequest(baseUrl)
    @Provides
    @Singleton
    @Named("baseUrl")
    fun urlProvider(): String = ApiConstants.API_URL_MOCKY

    @Provides
    fun remoteDataSourceProvider(
        serverRequest: ServerRequest,
    ): RemoteMockyDataSource = MockyRetrofitDataSource(serverRequest)
}