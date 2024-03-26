package com.coding.social.di

import com.coding.core.data.service.AuthenticationServiceImpl
import com.coding.core.domain.service.AuthenticationService
import com.coding.onboarding_domain.use_case.PasswordStrengthValidator
import com.coding.timeline_data.service.StoreServiceImpl
import com.coding.timeline_domain.service.StoreService
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

    @Provides
    @Singleton
    fun provideStoreService(authenticationService: AuthenticationService): StoreService {
        return StoreServiceImpl(authenticationService)
    }

    @Provides
    @Singleton
    fun providePasswordStrengthValidator(): PasswordStrengthValidator {
        return PasswordStrengthValidator()
    }

}