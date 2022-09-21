package br.puc.simpleservice.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import br.puc.simpleservice.model.Car;

@Path("/cars")
public class CarController {

    private static List<Car> cars = new ArrayList<Car>();

    @Context
    private UriInfo context;

    @GET
    public String all() {
        return cars.toString();
    }

    @POST
    public void save(String name) {
        Car car = new Car();
        car.setName(name);
        cars.add(car);
    }
}
