package com.example.tasksocial.common

import androidx.lifecycle.SavedStateHandle
import com.example.tasksocial.model.MockData
import com.example.tasksocial.viewmodel.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTestRepository(handle: SavedStateHandle): MainRepository =
        MockData()
}