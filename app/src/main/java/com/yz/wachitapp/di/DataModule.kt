package com.yz.wachitapp.di

import com.yz.data.repository.UsersRepositoryImpl
import com.yz.data.repository.VideosRepositoryImpl
import com.yz.domain.repository.UsersRepository
import com.yz.domain.repository.VideosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindVideosRepository(
        videosRepositoryImpl: VideosRepositoryImpl
    ): VideosRepository

    @Binds
    abstract fun bindUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository

}