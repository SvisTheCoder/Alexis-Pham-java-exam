package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class Partie4 {

    ToDoubleFunction<Trip> getPrice =
            trip -> trip.price();

    Function<Trip, String> getCity =
            trip -> trip.city();

    Predicate<Trip> isPremiumTrip =
            trip -> trip.price() > 30 && trip.rating() > 4;

    public double totalRevenueSequential(List<Trip> trips) {
        return trips.stream()
                .mapToDouble(getPrice)
                .sum();
    }

    public double totalRevenueParallel(List<Trip> trips) {
        return trips.parallelStream()
                .mapToDouble(getPrice)
                .sum();
    }

    public Map<String, Long> countByCityParallel(List<Trip> trips) {
        return trips.parallelStream()
                .collect(Collectors.groupingBy(
                        getCity,
                        Collectors.counting()
                ));
    }

    public List<Trip> premiumTripsParallel(List<Trip> trips) {
        return trips.parallelStream()
                .filter(isPremiumTrip)
                .toList();
    }
}