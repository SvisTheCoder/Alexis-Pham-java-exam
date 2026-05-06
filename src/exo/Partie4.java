package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class Partie4 {

    ToDoubleFunction<Trip> getPrice =
            trip -> trip.price();

    Predicate<Trip> isPremiumTrip =
            trip -> trip.price() > 30 && trip.rating() > 4;

    public double totalRevenueSequential(List<Trip> trips) {
        // Revenu total en stream séquentiel
        return trips.stream()
                .mapToDouble(getPrice)
                .sum();
    }

    public double totalRevenueParallel(List<Trip> trips) {
        // Revenu total en stream parallèle
        return trips.parallelStream()
                .mapToDouble(getPrice)
                .sum();
    }

    public Map<String, Long> countByCityParallel(List<Trip> trips) {
        // Nombre de trajets par ville en parallèle
        return trips.parallelStream()
                .collect(Collectors.groupingBy(
                        Trip::city,
                        Collectors.counting()
                ));
    }

    public List<Trip> premiumTripsParallel(List<Trip> trips) {
        // prix > 30 et rating > 4
        return trips.parallelStream()
                .filter(isPremiumTrip)
                .toList();
    }
}