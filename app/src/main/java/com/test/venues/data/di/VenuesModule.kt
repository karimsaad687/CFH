package com.test.venues.data.di

import com.test.venues.data.VenuesApi
import com.test.venues.data.repository.VenuesRepositoryImpl
import com.test.venues.domain.repository.VenuesRepository
import com.test.venues.domain.use_case.venues_usecase.VenuesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VenuesModule {

    @Provides
    @Singleton
    fun provideVenuesApi():VenuesApi{
        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VenuesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVenuesRepository(api:VenuesApi):VenuesRepository{
        return VenuesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideVenuesUseCase(repository: VenuesRepository): VenuesUsecase {
        return VenuesUsecase(repository)
    }
}