package com.efly.di.component

import com.efly.base.BaseRepoistory
import com.efly.di.modules.ContextModule
import com.efly.di.modules.NetworkModule
import com.efly.di.modules.ReposModule
import com.efly.viewmodels.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (ReposModule::class), (ContextModule::class)])
interface ViewModelComponent {

    fun inject(baseRepoistory: BaseRepoistory)

    fun inject(splashViewModel: SplashViewModel)
    fun inject(mainViewModel: MainViewModel)
    fun inject(cartViewModel: CartViewModel)
    fun inject(storesViewModel: StoresViewModel)
    fun inject(accountViewModel: AccountViewModel)
    fun inject(productViewModel: ProductViewModel)

}