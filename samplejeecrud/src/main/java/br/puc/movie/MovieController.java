package br.puc.movie;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("movies")
@Produces({"application/json"})
public class MovieController {

    @EJB
    private MovieService movieService;

    @GET
    @Path("{id}")
    public Movie find(@PathParam("id") Long id) {
        return movieService.find(id);
    }

    @GET
    public List<Movie> getMovies(@QueryParam("first") Integer first, @QueryParam("max") Integer max,
                                 @QueryParam("field") String field, @QueryParam("searchTerm") String searchTerm) {
        return movieService.getMovies(first, max, field, searchTerm);
    }

    @POST
    @Consumes("application/json")
    public Movie addMovie(Movie movie) {
        movieService.addMovie(movie);
        return movie;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Movie editMovie(Movie movie) {
        movieService.editMovie(movie);
        return movie;
    }

    @DELETE
    @Path("{id}")
    public void deleteMovie(@PathParam("id") long id) {
        movieService.deleteMovie(id);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int count(@QueryParam("field") String field, @QueryParam("searchTerm") String searchTerm) {
        return movieService.count(field, searchTerm);
    }
    
    @POST
    @Path("load")
    public void load() {
    	movieService.addMovie(new Movie("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
    	movieService.addMovie(new Movie("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
    	movieService.addMovie(new Movie("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
    	movieService.addMovie(new Movie("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
    	movieService.addMovie(new Movie("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        movieService.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        movieService.addMovie(new Movie("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));
    }

}