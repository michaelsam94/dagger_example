package com.example.dagger_example

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "6bdd92e829e5beb0f2902f834db79e10"

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = with(OkHttpClient.Builder()) {
            this.addInterceptor {
                val req = it.request()
                val originalHttpUrl = req.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                val reqBuilder = req.newBuilder().url(url)
                val newReq = reqBuilder.build()
                it.proceed(newReq)
            }
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideRetrofitMovieService(okHttpClient: OkHttpClient): MovieService {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(MovieService::class.java)
    }


}