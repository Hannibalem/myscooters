package scooters.coup.com.coup.usecase

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import scooters.coup.com.coup.model.Scooter
import scooters.coup.com.coup.wrapper.GoogleMapWrapper

import javax.inject.Inject

const val MAP_ZOOM = 15f

class BuildMapUseCase @Inject constructor() {

    fun execute(map: GoogleMapWrapper, scooters: List<Scooter>) {
        addPins(map, scooters)
    }

    private fun addPins(map: GoogleMapWrapper, scooters: List<Scooter>) {
        var location: LatLng? = null
        for (scooter in scooters) {
            location = buildLocation(scooter).also { it.buildMarker(scooter).let { map.addMarker(it) } }
        }
        location?.let { map.moveCamera(getCameraUpdate(it)) }
    }

    private fun buildLocation(scooter: Scooter)
            = LatLng(scooter.location?.lat ?: 0.0, scooter.location?.lng ?: 0.0)

    private fun LatLng.buildMarker(scooter: Scooter) =
            MarkerOptions()
                    .position(this)
                    .title(scooter.model)
                    .icon(getIcon(scooter.energy_level))

    private fun getPinColor(energyLevel: Int? = 0) =
            when(energyLevel) {
                in 0..30 -> BitmapDescriptorFactory.HUE_RED
                in 31..50 -> BitmapDescriptorFactory.HUE_YELLOW
                else -> BitmapDescriptorFactory.HUE_GREEN
            }

    fun getCameraUpdate(location: LatLng?): CameraUpdate
            = CameraUpdateFactory.newLatLngZoom(location, MAP_ZOOM)

    fun getIcon(energyLevel: Int?): BitmapDescriptor
            = BitmapDescriptorFactory.defaultMarker(getPinColor(energyLevel))
}