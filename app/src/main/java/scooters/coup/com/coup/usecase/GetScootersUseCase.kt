package scooters.coup.com.coup.usecase

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import scooters.coup.com.coup.model.Scooter
import scooters.coup.com.coup.service.MapsService
import javax.inject.Inject

class GetScootersUseCase @Inject constructor(val service: MapsService) {

    fun execute(): Single<List<Scooter>> =
            service.listScooters()
                    .map { it.data?.scooters ?: listOf() }
                    .subscribeOn(Schedulers.io())
}