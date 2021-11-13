package com.yz.wachitapp.di

import com.yz.data.datasource.AssetsDataSource
import com.yz.data.datasource.AssetsDataSourceImpl
import com.yz.data.datasource.VideosDataSource
import com.yz.data.datasource.VideosDataSourceImpl
import com.yz.data.repository.VideosRepositoryImpl
import com.yz.domain.repository.VideosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAssetsDataSource(
        assetsDataSourceImpl: AssetsDataSourceImpl
    ): AssetsDataSource

}