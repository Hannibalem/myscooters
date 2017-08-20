package scooters.coup.com.coup

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.MarkerOptions
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import scooters.coup.com.coup.model.Scooter
import scooters.coup.com.coup.usecase.BuildMapUseCase
import scooters.coup.com.coup.wrapper.GoogleMapWrapper

class BuildMapUseCaseTest {

    val map = mock(GoogleMapWrapper::class.java)
    val scooters = mutableListOf<Scooter>()
    val spiedTested = spy(BuildMapUseCase())

    @Test
    fun should_get_list_scooters() {
        //Given
        val scooter = Scooter.NONE
        scooters.add(scooter)

        val icon = mock(BitmapDescriptor::class.java)
        doReturn(icon).`when`(spiedTested).getIcon(scooter.energy_level)
        val cameraUpdate = mock(CameraUpdate::class.java)
        doReturn(cameraUpdate).`when`(spiedTested).getCameraUpdate(any())

        //When
        spiedTested.execute(map, scooters)

        //Then
        ArgumentCaptor.forClass(MarkerOptions::class.java).apply {
            verify(map).addMarker(capture())
            assertEquals(value.position.latitude, scooter.location!!.lat)
            assertEquals(value.position.longitude, scooter.location!!.lng)
            assertEquals(value.title, scooter.model)
            assertEquals(value.icon, icon)
        }

        verify(map).moveCamera(cameraUpdate)
    }
}