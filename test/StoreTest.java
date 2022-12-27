package test;

import main.models.Movie;
import main.models.Store;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;

public class StoreTest {

    Store store;

    @Before
    public void setup() {
        store = new Store();
        store.addMovie(new Movie("Forrest Gump", "Bluray", 9.4));
        store.addMovie(new Movie("Fight Club", "DVD", 9.1));
    }

    @Test
    public void sellMovieTest() {
        store.sellMovie("Fight Club");
        assertFalse(store.getStore().contains(new Movie("Fight Club", "DVD", 9.1)));
    }

    @Test
    public void movieAdded() {
        assertTrue(store.getStore().contains(new Movie("Fight Club", "DVD", 9.1)));
    }

    @Test
    public void rentMovieTest() {
        store.rentMovie("Forrest Gump");
        assertFalse(store.getStore().get(0).isAvailable());
    }

    @Test
    public void returnMovieTest() {
        store.returnMovie("Forrest Gump");
        assertTrue(store.getStore().get(0).isAvailable());
    }

    @Test(expected = IllegalStateException.class)
    public void movieNotInStock() {
        store.rentMovie("Forrest Gump");
        store.sellMovie("Forrest Gump");
    }

}
