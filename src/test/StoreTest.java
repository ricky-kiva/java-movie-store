package test;

import main.models.Movie;
import main.models.Store;
import main.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;

public class StoreTest {

    static Store store = new Store();

    @Before
    public static void setup() {
        store.addMovie(new Movie("Forrest Gump", "Bluray", 9.4));
        store.addMovie(new Movie("Fight Club", "DVD", 9.1));
    }

    @Test
    public static void movieAdded() {
        // assertTrue(store.contains(new Movie("Fight Club", "DVD", 9.1)));
    }

}
