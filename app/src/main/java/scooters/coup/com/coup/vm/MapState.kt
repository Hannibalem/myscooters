package scooters.coup.com.coup.vm

import android.os.Bundle
import com.google.android.gms.maps.MapView

enum class MapState {
    CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    DESTROY,
    SAVE_INSTANCE,
    LOW_MEMORY,
    NONE
}

fun MapView.setLifeCycleState(mapState: MapState, savedInstanceState: Bundle?) {
    when(mapState) {
        MapState.CREATE -> onCreate(savedInstanceState)
        MapState.START -> onStart()
        MapState.RESUME -> onResume()
        MapState.PAUSE -> onPause()
        MapState.STOP -> onStop()
        MapState.DESTROY -> onDestroy()
        MapState.LOW_MEMORY -> onLowMemory()
        MapState.SAVE_INSTANCE -> onSaveInstanceState(savedInstanceState)
    }
}