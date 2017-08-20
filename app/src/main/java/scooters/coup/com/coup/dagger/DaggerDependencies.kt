package scooters.coup.com.coup.dagger

import android.app.Application
import scooters.coup.com.coup.MapsActivity
import scooters.coup.com.coup.dagger.component.ApplicationComponent
import scooters.coup.com.coup.dagger.component.DaggerApplicationComponent
import scooters.coup.com.coup.dagger.module.ApplicationModule

class DaggerDependencies {

    companion object {

        lateinit var injector: ApplicationComponent
            private set

        fun init(application: Application) {
            injector = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
        }

        fun inject(activity: MapsActivity) {
            injector.inject(activity)
        }
    }
}