package exo;

import models.Trip;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Partie3 {

    Comparator<Trip> byPrice =
            Comparator.comparingDouble(trip -> trip.price());

    Comparator<Trip> byRating =
            Comparator.comparingDouble(trip -> trip.rating());

    public List<Trip> top10ExpensiveTrips(List<Trip> trips) {
        // Top 10 des trajets les plus chers
        return trips.stream()
                .sorted(byPrice.reversed())
                .limit(10)
                .toList();
    }

    public Optional<Trip> bestTrip(List<Trip> trips) {
        // Trajet avec la meilleure note
        return trips.stream()
                .max(byRating);
    }
}