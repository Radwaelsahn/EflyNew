package com.efly.di.modules

import com.efly.repositories.HomeRepository
import com.efly.repositories.RestRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ReposModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideHomeRepo(): HomeRepository {
        return HomeRepository()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRestRepo(): RestRepository {
        return RestRepository()
    }

}