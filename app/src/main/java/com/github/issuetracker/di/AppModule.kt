package com.github.issuetracker.di

import android.content.Context
import androidx.room.Room
import com.github.issuetracker.BuildConfig
import com.github.issuetracker.api.ApiHelper
import com.github.issuetracker.api.ApiHelperImpl
import com.github.issuetracker.api.ApiService
import com.github.issuetracker.db.GithubIssueTrackerDataBase
import com.github.issuetracker.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideGithubIssueTrackerDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GithubIssueTrackerDataBase::class.java,
        Constants.GITHUB_ISSUE_TRACKER_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesGithubIssueTrackerDao(githubIssueTrackerDataBase: GithubIssueTrackerDataBase) = githubIssueTrackerDataBase.getGithubIssueTrackerDao()

}