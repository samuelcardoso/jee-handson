package br.puc.movie;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import br.puc.movie.Movie;
import br.puc.movie.MovieService;
import jakarta.ejb.embeddable.EJBContainer;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieServiceTest {

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
        final MovieService movies = (MovieService) ejbContainer.getContext().lookup("java:global/samplejeecrud/MovieService!br.puc.movie.MovieService");
        movies.clean();
    }
    
    @Test
    public void testShouldAddAMovie() throws Exception {
        final MovieService movies = (MovieService) ejbContainer.getContext().lookup("java:global/samplejeecrud/MovieService!br.puc.movie.MovieService");

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