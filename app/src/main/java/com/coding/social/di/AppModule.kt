package com.coding.social.di

import com.coding.onboarding_data.service.AuthenticationServiceImpl
import com.coding.onboarding_domain.service.AuthenticationService
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