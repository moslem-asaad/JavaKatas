package katas.exercises;

import katas.exercises.movieRental.Rental;
import katas.exercises.movieRental.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MovieRentalCustomerTest {
    @Test
    public void test() {
        MovieRentalCustomer customer = new MovieRentalCustomer("Bob");
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Golden Eye", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Short New", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Long New", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));

        String expected = "" +
                "Rental Record for Bob\n" +
                "\tJaws\t2.0\n" +
                "\tGolden Eye\t3.5\n" +
                "\tShort New\t3.0\n" +
                "\tLong New\t6.0\n" +
                "\tBambi\t1.5\n" +
                "\tToy Story\t3.0\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    void testStatement() {
        MovieRentalCustomer customer = new MovieRentalCustomer("martin");
        customer.addRental(new Rental(new Movie("Ran", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Trois Couleurs: Bleu", Movie.NEW_RELEASE), 1));

        String expected = "Rental Record for martin\n" +
                "\tRan\t3.5\n" +
                "\tTrois Couleurs: Bleu\t3.0\n" +
                "Amount owed is 6.5\n" +
                "You earned 2 frequent renter points";
        assertEquals(expected, customer.statement());
    }

    @Test
    void testHtmlStatement() {
        MovieRentalCustomer customer = new MovieRentalCustomer("martin");
        customer.addRental(new Rental(new Movie("Ran", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Trois Couleurs: Bleu", Movie.NEW_RELEASE), 1));

        String expected = "<h1>Rental Record for <em>martin</em></h1>\n" +
                "<table>\n" +
                "<tr><td>Ran</td><td>3.5</td></tr>\n" +
                "<tr><td>Trois Couleurs: Bleu</td><td>3.0</td></tr>\n" +
                "</table>\n" +
                "<p>Amount owed is <em>6.5</em></p>\n" +
                "<p>You earned <em>2</em> frequent renter points</p>";
        assertEquals(expected, customer.htmlStatement());
    }

}
