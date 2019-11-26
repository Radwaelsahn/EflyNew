//@file:JvmName("NetworkModuleKt")
//
//package com.efly.di
//
//import com.efly.network.RestApi
//import com.efly.utils.BASE_URL
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import org.koin.android.ext.koin.androidApplication
//import org.koin.android.ext.koin.androidContext
//import org.koin.core.module.Module
//import org.koin.dsl.module
//import retrofit2.Retrofit
//import retrofit2.categoriesAdapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//
//// Draft
//val networkModule = module {
//
//    single { provideDefaultOkhttpClient() }
//    single{ provideRetrofit(get()) }
//    single{ provideRestApi(get()) }
//    single{androidContext()}
//
//}
//
//val appComponent: List<Module> = listOf(networkModule)
//
//
//
//
//
//fun provideDefaultOkhttpClient(): OkHttpClient {
//    val client = OkHttpClient.Builder()
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.level = HttpLoggingInterceptor.Level.BODY
//    client.addInterceptor(interceptor)
//    return client.build()
//}
//fun provideRetrofit(client: OkHttpClient): Retrofit {
//    return Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//}
//fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)
