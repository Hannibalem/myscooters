package scooters.coup.com.coup.service

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import scooters.coup.com.coup.model.FeedAPI

interface MapsService {

    @GET("scooters.json")
    fun listScooters(): Single<FeedAPI>
}