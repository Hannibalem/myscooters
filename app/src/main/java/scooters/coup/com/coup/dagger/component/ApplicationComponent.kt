package scooters.coup.com.coup.dagger.component

import dagger.Component
import scooters.coup.com.coup.MapsActivity
import scooters.coup.com.coup.dagger.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(activity: MapsActivity)
}