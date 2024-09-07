package com.example.coinwave.di

import com.example.coinwave.common.Constants
import com.example.coinwave.data.service.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideCoinApi(retrofit: Retrofit): CoinService {
    return retrofit.create(CoinService::class.java)
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create()).build()
  }

  @Singleton
  @Provides
  fun provideHttpClient(): OkHttpClient {
    return OkHttpClient
      .Builder()
      .readTimeout(60, TimeUnit.SECONDS)
      .connectTimeout(60, TimeUnit.SECONDS)
      .build()
  }
}
