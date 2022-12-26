package main.models;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Store {
    ArrayList<Movie> movies;

    public Store() {
        this.movies = new ArrayList<Movie>();
    }

    public Store(Store source) {
        this.movies = new ArrayList<>(source.movies);
    }

    public ArrayList<Movie> getStore() {
        return this.movies;
    }

    public Movie getMovie(int index) {
        return new Movie(this.movies.get(index));
    }

    public void setMovie(int index, Movie movie) {
        this.movies.set(index, new Movie(movie));
    }

    public void addMovie(Movie movie) {
        this.movies.add(new Movie(movie));
    }

    public void sellMovie(String name) {
        this.movies.removeIf(movie -> (movie.getName()).equals(name));
    }

    public void rentMovie(String name) {
        int index = this.getMovieIndex(name);
        if (index >= 0 || index < this.movies.size()) {
            this.movies.get(index).setAvailable(false);
        } else {
            System.out.println("We got no movie as such by this time.");
        }
    }

    public void returnMovie(String name) {
        int index = this.getMovieIndex(name);
        if (index >= 0 || index < this.movies.size()) {
            this.movies.get(index).setAvailable(true);
        } else {
            System.out.println("Nothing to return.");
        }
    }

    public int getMovieIndex(String name) {
        return IntStream.range(0, this.movies.size())
        .filter(index -> this.movies.get(index).getName() == name)
        .findFirst()
        .orElse(-1);
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < this.movies.size(); i++) {
            temp += this.movies.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }

}
