package com.yz.wachitapp.di

import com.yz.data.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindMockDataDataSource(
        mockDataDataSourceImpl: MockDataDataSourceImpl
    ): MockDataDataSource

    @Binds
    abstract fun bindUsersDataSource(
        usersDataSourceImpl: UsersDataSourceImpl
    ): UsersDataSource

    @Binds
    abstract fun bindVideosDataSource(
        videosDataSourceImpl: VideosDataSourceImpl
    ): VideosDataSource

}