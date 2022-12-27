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

        displayStore(store);

        Scanner scan = new Scanner(System.in);

        String choice = getChoice(scan, null);

        while (
            choice.equals("add") || 
            choice.equals("edit") ||
            choice.equals("buy") ||
            choice.equals("rent") ||
            choice.equals("return")
        ) {
            switch(choice) {
                case "add":
                    choice = caseAdd(choice, scan,store);
                    break;
                case "edit":
                    choice = caseEdit(choice, scan, store);
                    break;
                case "buy":
                    choice = caseBuy(choice, scan, store);
                default:
                    break;
            }
        }

        scan.close();

    }

    public static String caseAdd(String choice, Scanner scan, Store store) {
        while(choice.equals("add")) {

            String movieName = getMovieName(scan);
            String movieFormat = getMovieFormat(scan);
            double movieRating = getMovieRating(scan);
    
            store.addMovie(new Movie(movieName, movieFormat, movieRating));
    
            displayStore(store);
            choice = getChoice(scan, choice);
        }
        return choice;
    }

    public static String caseEdit(String choice, Scanner scan, Store store) {
        while (choice.equals("edit")) {

            String movieName = getMovieFromStore(scan, store);
            double movieRating = getMovieRating(scan);

            store.getStore().get(store.getMovieIndex(movieName.toLowerCase())).setRating(movieRating);

            displayStore(store);
            choice = getChoice(scan, choice);
        }
        return choice;
    }

    public static String caseBuy(String choice, Scanner scan, Store store) {
        while(choice.equals("buy")) {

            String movieName = getMovieFromStore(scan, store);
            store.sellMovie(movieName);
    
            displayStore(store);
            choice = getChoice(scan, choice);
        }
        return choice;
    }

    public static String getChoice(Scanner scan, String choice) {
        System.out.print("Menu (Please choose one):\n>\t[add]\t[edit]\t[buy]\t[rent]\t[return]\t[exit]\n>\tYour Option: ");
        choice = scan.nextLine();
        choice = choice.toLowerCase();
        return choice;
    }

    /*
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
    */

    public static String getMovieName(Scanner scan) {
        while(true) {
            System.out.print("\nWhat's the name of the movie? ");
            String movieName = scan.nextLine();
            if (!(movieName == null || movieName.isBlank())) {
                return movieName;
            } else {
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
            System.out.print("\nAnd how's the rating (0-10) ? ");
            if (scan.hasNextDouble()) {
                double rating = scan.nextDouble();
                scan.nextLine(); // escape buffer trap
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

    public static String getMovieFromStore(Scanner scan, Store store) {
        while(true) {
            String movieName = getMovieName(scan);
            if (store.getMovieIndex(movieName.toLowerCase()) == -1) {
                System.out.println("There's no such movie!");
                continue;
            }
            return movieName;
        }
    }

    public static void displayStore(Store store) {
        System.out.println("\n********************************MOVIE STORE*******************************\n");
        for (int i = 0; i < store.getStore().size(); i++) {
            System.out.println((i+1) + "." + store.getMovie(i));
        }
    }

}