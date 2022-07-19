package com.my.employees_root.data

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
import com.my.core.BuildConfigWrapper
import com.my.core.FeatureScope
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.*
import com.my.employees.domain.FilterParamsRepository
import com.my.specialties.data.SaveSpecialties
import com.my.specialties.data.SpecialtyConverter
import com.my.specialties.domain.ChooseSpecialtiesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module(includes = [EmployeesRootDataModuleBinds::class])
class NetworkModule {

    @[Provides FeatureScope]
    fun provideEmployeesRootRepository(
        remoteDataSource: EmployeesRemoteDataSource,
        employeesConverter: EmployeesConverter,
        specialtyConverter: SpecialtyConverter,
        saveSpecialties: SaveSpecialties,
        employeesStorage: EmployeesStorage,
        filterParamsMemoryCache: FilterParamsMemoryCache
    ) = EmployeesRootRepository(
        remoteDataSource,
        employeesConverter,
        specialtyConverter,
        saveSpecialties,
        employeesStorage,
        filterParamsMemoryCache
    )

    @Provides
    internal fun provideEmployeesRemoteDataSource(employeesService: EmployeesService) =
        EmployeesRemoteDataSource(employeesService)

    @Provides
    internal fun provideFilterParamsMemoryCache() = FilterParamsMemoryCache()

    @Provides
    internal fun provideEmployeesService(retrofit: Retrofit) =
        retrofit.create(EmployeesService::class.java)

    @Provides
    internal fun provideRetrofit(
        buildConfigWrapper: BuildConfigWrapper,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(buildConfigWrapper.baseUrl())
        .client(okHttpClient)
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

@Module
abstract class EmployeesRootDataModuleBinds {

    @Binds
    abstract fun bindEmployeesRepository(implementation: EmployeesRootRepository): FilterParamsRepository

    @Binds
    abstract fun bindSpecialtiesRepository(implementation: EmployeesRootRepository): ChooseSpecialtiesRepository
}