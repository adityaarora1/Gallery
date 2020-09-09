package com.sample.gallery

import android.content.Context
import com.sample.base.AuthenticationInterceptor
import com.sample.base.BuildConfig.API_URL
import com.sample.base.NetworkConnectionInterceptor
import com.sample.gallery.data.remote.RetrofitService
import com.sample.gallery.data.repository.GalleryRepositoryImpl
import com.sample.gallery.domain.usecase.GetGalleryUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesAuthenticationInterceptor(): AuthenticationInterceptor = AuthenticationInterceptor()

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(context)

    @Singleton
    @Provides
    fun provideClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            val logger = object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) = Timber.d(message)
            }
            val interceptor = HttpLoggingInterceptor(logger).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            this.addInterceptor(interceptor)
            this.addInterceptor(authenticationInterceptor)
            this.addInterceptor(networkConnectionInterceptor)
        }
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    internal fun provideApi(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)

    @Singleton
    @Provides
    internal fun provideGetGalleryUseCase(
        galleryRepository: GalleryRepositoryImpl
    ): GetGalleryUseCase =
        GetGalleryUseCase(galleryRepository)
}