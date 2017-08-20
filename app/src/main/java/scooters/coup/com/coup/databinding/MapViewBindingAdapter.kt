package scooters.coup.com.coup.databinding

import android.databinding.BindingAdapter
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import scooters.coup.com.coup.vm.ViewModel

const val MAP_LISTENER = "mapListener"

@BindingAdapter(MAP_LISTENER)
fun MapView._bindMapListener(mapListener: OnMapReadyCallback?) {
    mapListener?.let {
        if (it != ViewModel.EMPTY_MAP_LISTENER) getMapAsync(it)
    }
}