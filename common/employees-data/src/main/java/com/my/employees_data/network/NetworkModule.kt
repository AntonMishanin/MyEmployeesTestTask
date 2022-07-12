package com.my.employees_data.network

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
import com.my.core.BuildConfigWrapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
internal class NetworkModule {

    @Provides
    internal fun provideEmployeesRemoteDataSource(employeesService: EmployeesService) =
        EmployeesRemoteDataSource(employeesService)

    @Provides
    internal fun provideEmployeesService(retrofit: Retrofit) =
        retrofit.create(EmployeesService::class.java)

    @Provides
    internal fun provideRetrofit(
        buildConfigWrapper: BuildConfigWrapper,
        okHttpClient: OkHttpClient,
        rxAdapterFactory: CallAdapter.Factory,
        gsonConverterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(buildConfigWrapper.baseUrl())
        .client(okHttpClient)
        .addCallAdapterFactory(rxAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    internal fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    internal fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(TIMEOUT_KEY) timeout: Long
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(timeout, TimeUnit.MILLISECONDS)
        .readTimeout(timeout, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Named(TIMEOUT_KEY)
    internal fun provideTimeout() = 120_000L

    @Provides
    internal fun provideLogging(buildConfigWrapper: BuildConfigWrapper) =
        HttpLoggingInterceptor().apply {
            level = when (buildConfigWrapper.isDebug()) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }

    private companion object {
        private const val TIMEOUT_KEY = "Timeout"
    }
}