package com.yz.wachitapp.di

import com.yz.data.datasource.AssetsDataSource
import com.yz.data.datasource.AssetsDataSourceImpl
import com.yz.presentation.navigation.Navigator
import com.yz.wachitapp.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAssetsDataSource(
        assetsDataSourceImpl: AssetsDataSourceImpl
    ): AssetsDataSource


    @Binds
    abstract fun bindNavigator(
        navigatorImpl: NavigatorImpl
    ): Navigator


}