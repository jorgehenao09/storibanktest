package com.jhtest.storibanktest.di

import com.jhtest.storibanktest.data.mapper.BankTransactionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {
    @Provides
    @ViewModelScoped
    fun provideBankTransactionMapper() = BankTransactionMapper()
}
