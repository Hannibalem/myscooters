package scooters.coup.com.coup.wrapper

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions

//Needed for Testing since GoogleMap can't be mocked/spied
class GoogleMapWrapper(val googleMap: GoogleMap) {

    fun addMarker(markerOptions: MarkerOptions?) {
        googleMap.addMarker(markerOptions)
    }

    fun moveCamera(cameraUpdate: CameraUpdate?) {
        googleMap.moveCamera(cameraUpdate)
    }
}