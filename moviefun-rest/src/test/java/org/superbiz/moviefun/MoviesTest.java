package org.superbiz.moviefun;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jakarta.ejb.embeddable.EJBContainer;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoviesTest {

    private static EJBContainer ejbContainer;

    @BeforeClass
    public static void setUp() throws Exception {
        ejbContainer = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDown() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }

    @Before
    @After
    public void clean() throws Exception {
        final MoviesBean movies = (MoviesBean) ejbContainer.getContext().lookup("java:global/moviefun-rest/MoviesBean!org.superbiz.moviefun.MoviesBean");
        movies.clean();
    }

    @Test
    public void testShouldAddAMovie() throws Exception {
        final MoviesBean movies = (MoviesBean) ejbContainer.getContext().lookup("java:global/moviefun-rest/MoviesBean!org.superbiz.moviefun.MoviesBean");

        final Movie movie = new Movie();
        movie.setDirector("Michael Bay");
        movie.setGenre("Action");
        movie.setRating(9);
        movie.setTitle("Bad Boys");
        movie.setYear(1995);
        movies.addMovie(movie);

        assertEquals(1, movies.count("title", "a"));
        final List<Movie> moviesFound = movies.getMovies(0, 100, "title", "Bad Boys");
        assertEquals(1, moviesFound.size());
        assertEquals("Michael Bay", moviesFound.get(0).getDirector());
        assertEquals("Action", moviesFound.get(0).getGenre());
        assertEquals(9, moviesFound.get(0).getRating());
        assertEquals("Bad Boys", moviesFound.get(0).getTitle());
        assertEquals(1995, moviesFound.get(0).getYear());
    }

}