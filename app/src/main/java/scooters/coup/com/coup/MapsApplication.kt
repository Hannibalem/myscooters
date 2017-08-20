package scooters.coup.com.coup

import android.app.Application
import android.content.Context
import scooters.coup.com.coup.dagger.DaggerDependencies

class MapsApplication: Application() {

    companion object {

        private lateinit var instance: MapsApplication

        fun getAppContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        DaggerDependencies.init(this)
        instance = this
    }
}