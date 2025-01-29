package katas.exercises.movieRental;

/**
 * The rental class represents a customer renting a movie.
 */
public class Rental {

    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public double getAmount() {
        return _movie.calculateRentalAmount(_daysRented);
    }

    public int getFrequentRenterPoints() {
        return _movie.calculateFrequentRenterPoints(_daysRented);
    }
}
