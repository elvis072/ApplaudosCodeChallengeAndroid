package com.example.applaudoscodechallengeandroid.di

import com.example.applaudoscodechallengeandroid.data.remote.service.TvShowService
import com.example.applaudoscodechallengeandroid.data.repository.TvShowRepositoryImpl
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTvShowRepository(tvShowService: TvShowService) : TvShowRepository {
        return TvShowRepositoryImpl(tvShowService)
    }
}