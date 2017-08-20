package scooters.coup.com.coup.model

data class FeedAPI(val data: Scooters? = null)

data class Scooters(val scooters: List<Scooter>? = null)

data class Scooter(val id: String? = null,
                   val vin: String? = null,
                   val model: String? = null,
                   val market_id: String? = null,
                   val license_plate: String? = null,
                   val energy_level: Int? = null,
                   val distance_to_travel: Double? = null,
                   val location: Location? = null) {

    companion object {
        val NONE = Scooter("", "", "", "", "", 0, 0.0, Location.NONE)
    }
}

data class Location(val lng: Double? = null,
                    val lat: Double? = null) {

    companion object {

        val NONE = Location(0.0, 0.0)
    }
}