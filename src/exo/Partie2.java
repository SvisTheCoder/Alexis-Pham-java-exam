package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Partie2 {

    Function<Trip, String> getCity =
            trip -> trip.city();

    Function<Trip, String> getDriverId =
            trip -> trip.driverId();

    Function<Trip, Double> getPrice =
            trip -> trip.price();

    Function<Trip, Double> getDuration =
            trip -> trip.durationMin();

    public Map<String, Long> countByCity(List<Trip> trips) {
        // Nombre de trajets par ville
        return trips.stream()
                .collect(Collectors.groupingBy(
                        getCity,
                        Collectors.counting()
                ));
    }

    public Map<String, Double> revenueByDriver(List<Trip> trips) {
        // Revenu total par chauffeur
        return trips.stream()
                .collect(Collectors.groupingBy(
                        getDriverId,
                        Collectors.summingDouble(getPrice::apply)
                ));
    }

    public Map<String, Double> avgDurationByCity(List<Trip> trips) {
        // Durée moyenne par ville
        return trips.stream()
                .collect(Collectors.groupingBy(
                        getCity,
                        Collectors.averagingDouble(getDuration::apply)
                ));
    }
}