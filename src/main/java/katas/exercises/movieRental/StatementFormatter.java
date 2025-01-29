package katas.exercises.movieRental;

import katas.exercises.MovieRentalCustomer;

public class StatementFormatter {

    public static String generatePlainTextStatement(MovieRentalCustomer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (Rental rental : customer.getRentals()) {
            double thisAmount = rental.getAmount();
            frequentRenterPoints += rental.getFrequentRenterPoints();
            result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    public static String generateHtmlStatement(MovieRentalCustomer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("<h1>Rental Record for <em>").append(customer.getName()).append("</em></h1>\n<table>\n");

        for (Rental rental : customer.getRentals()) {
            double thisAmount = rental.getAmount();
            frequentRenterPoints += rental.getFrequentRenterPoints();
            result.append("<tr><td>").append(rental.getMovie().getTitle()).append("</td><td>").append(thisAmount).append("</td></tr>\n");
            totalAmount += thisAmount;
        }

        result.append("</table>\n<p>Amount owed is <em>").append(totalAmount).append("</em></p>\n");
        result.append("<p>You earned <em>").append(frequentRenterPoints).append("</em> frequent renter points</p>");
        return result.toString();
    }
}
