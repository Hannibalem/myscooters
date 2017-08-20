package scooters.coup.com.coup.vm

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.android.gms.maps.OnMapReadyCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import scooters.coup.com.coup.BR
import scooters.coup.com.coup.model.Scooter
import scooters.coup.com.coup.usecase.BuildMapUseCase
import scooters.coup.com.coup.usecase.GetScootersUseCase
import scooters.coup.com.coup.wrapper.GoogleMapWrapper

class ViewModel(val getScootersUseCase: GetScootersUseCase,
                val buildMapUseCase: BuildMapUseCase): BaseObservable() {

    private val disposable = CompositeDisposable()

    companion object {
        val EMPTY_MAP_LISTENER = OnMapReadyCallback { }
    }

    @Bindable
    var mapListener: OnMapReadyCallback = EMPTY_MAP_LISTENER
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.mapListener)
                notifyPropertyChanged(BR.contentReady)
            }
        }

    @Bindable
    var contentReady: Boolean = true
        get() = mapListener == EMPTY_MAP_LISTENER

    fun fetchScooters() {
        disposable.add(getScootersUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ updateScooters(it) }) { handleError(it) })
    }

    fun onDestroy() {
        disposable.clear()
    }

    private fun updateScooters(scooters: List<Scooter>) {
        mapListener = OnMapReadyCallback { buildMapUseCase.execute(GoogleMapWrapper(it), scooters) }
    }

    private fun handleError(throwable: Throwable) {
        System.out.print(throwable.message)
    }
}
