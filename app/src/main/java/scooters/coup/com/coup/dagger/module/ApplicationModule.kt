package scooters.coup.com.coup.dagger.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import scooters.coup.com.coup.vm.ViewModel
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import scooters.coup.com.coup.service.MapsService
import scooters.coup.com.coup.usecase.BuildMapUseCase
import scooters.coup.com.coup.usecase.GetScootersUseCase


@Module
class ApplicationModule(internal var application: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://app.joincoup.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideService(retrofit: Retrofit): MapsService {
        return retrofit.create(MapsService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideViewModel(getScootersUseCase: GetScootersUseCase,
                                  buildMapUseCase: BuildMapUseCase): ViewModel {
        return ViewModel(getScootersUseCase, buildMapUseCase)
    }
}