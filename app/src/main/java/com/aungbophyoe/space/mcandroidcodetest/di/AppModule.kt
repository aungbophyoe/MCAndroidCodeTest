package com.aungbophyoe.space.mcandroidcodetest.di

import android.content.Context
import com.aungbophyoe.space.mcandroidcodetest.BuildConfig
import com.aungbophyoe.space.mcandroidcodetest.data.remote.ApiService
import com.aungbophyoe.space.mcandroidcodetest.data.remote.paging.BeersPagingSource
import com.aungbophyoe.space.mcandroidcodetest.utility.mapper.NetworkMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun moshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()!!

    @Provides
    fun provideOkhttpClient(@ApplicationContext context: Context) : OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        if(BuildConfig.DEBUG){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.networkInterceptors().add(httpLoggingInterceptor)
            builder.addInterceptor(ChuckInterceptor(context))
        }
        return builder.build()
    }

    @Provides
    fun provideRetrofitBuilder(moshi: Moshi,client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PRODUCTION_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
    }

    @Provides
    fun provideApiService(retrofit: Retrofit.Builder) : ApiService {
        return retrofit
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideBeersPagingSource(apiService: ApiService,networkMapper: NetworkMapper) : BeersPagingSource{
        return BeersPagingSource(apiService, networkMapper)
    }
}