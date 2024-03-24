package com.coding.social.di

import com.coding.core.data.service.AuthenticationServiceImpl
import com.coding.core.domain.service.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthenticationService(): AuthenticationService {
        return AuthenticationServiceImpl()
    }
}