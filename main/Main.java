package main;

import java.util.Scanner;
import main.models.Movie;
import main.models.Store;

public class Main {
    
    public static void main(String[] args) {
        Movie[] arrMovies = new Movie[] {
            new Movie("The Shawshank Redemption", "Bluray", 9.2),
            new Movie("The Godfather", "Bluray", 9.1),
            new Movie("The Godfather: Part II", "DVD", 9.0),
            new Movie("The Dark Knight", "Bluray", 9.0),
            new Movie("Schindler's List", "DVD", 8.9),
            new Movie("The Lord of the Rings: The Return of the King", "Bluray", 8.9),
            new Movie("Pulp Fiction", "DVD", 8.8),
            new Movie("The Lord of the Rings: The Fellowship of the Ring", "DVD", 8.8)
        };

        Store store = new Store();

        for (Movie movie : arrMovies) {
            store.addMovie(movie);
        }

        System.out.println("\n********************************MOVIE STORE*******************************\n");

        for (int i = 0; i < store.getStore().size(); i++) {
            System.out.println((i+1) + "." + store.getMovie(i));
        }

        Scanner scan = new Scanner(System.in);

        System.out.print("\nTo edit a list, type 'continue': ");
        String cont = scan.next();

        while (cont.equals("continue")) {

            int index = getIndex(scan);
            scan.nextLine();

            System.out.print("\nWhat's the name of the movie? ");
            String movieName = scan.nextLine();
            String movieFormat = getMovieFormat(scan);
            double movieRating = getMovieRating(scan);

            store.setMovie(index, new Movie(movieName, movieFormat, movieRating));

            System.out.print("\n********************************MOVIE STORE*******************************\n");

            for (int i = 0; i < store.getStore().size(); i++) {
                System.out.println((i+1) + "." + store.getMovie(i));
            }

            System.out.print("\nTo edit a list, type 'continue': ");
            cont = scan.next();

        }

        scan.close();

    }

    public static int getIndex(Scanner scan) {
        while(true) {
            System.out.print("\nPlease choose which index to change: ");
            if (scan.hasNextInt()) {
                int index = scan.nextInt();
                if (index > 0) {
                    return index;
                } else {
                    continue;
                }
            } else {
                scan.next();
                continue;
            }
        }
    }

    public static String getMovieFormat(Scanner scan) {
        while(true) {
            System.out.print("\nWhat's the format? (Bluray / DVD) ");
            String movieFormat = scan.next();
            if (movieFormat.equals("Bluray") || movieFormat.equals("DVD")) {
                return movieFormat;
            } else {
                continue;
            }
        }
    }

    public static double getMovieRating(Scanner scan) {
        while(true) {
            System.out.print("\nAnd how's the rating? ");
            if (scan.hasNextDouble()) {
                double rating = scan.nextDouble();
                if (rating >= 0 && rating <= 10) {
                    return rating;
                } else {
                    continue;
                }
            } else {
                scan.next();
                continue;
            }
        }
    }

}