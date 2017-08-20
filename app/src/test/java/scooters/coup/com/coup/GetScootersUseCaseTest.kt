package scooters.coup.com.coup

import scooters.coup.com.coup.usecase.GetScootersUseCase
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import scooters.coup.com.coup.model.FeedAPI
import scooters.coup.com.coup.model.Scooter
import scooters.coup.com.coup.model.Scooters
import scooters.coup.com.coup.service.MapsService

class GetScootersUseCaseTest {

    val service = mock(MapsService::class.java)
    val tested = GetScootersUseCase(service)

   @Test
   fun should_get_list_scooters() {
       //Given
       val listScooters = listOf<Scooter>()
       val scooters = Scooters(listScooters)
       val feed = FeedAPI(scooters)
       `when`(service.listScooters()).thenReturn(Single.just(feed))
       val subscriber = TestObserver<List<Scooter>>()

       //When
       tested.execute().subscribe(subscriber)

       //Then
       subscriber.awaitTerminalEvent()
       subscriber.assertNoErrors()
       subscriber.assertValue(listScooters)
   }
}