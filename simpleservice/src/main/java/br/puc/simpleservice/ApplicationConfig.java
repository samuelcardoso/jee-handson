package br.puc.simpleservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import br.puc.simpleservice.controller.CarController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;

@Path("/rest")
@ApplicationPath("api")
public class ApplicationConfig extends Application {

	@GET
	@Path("/hello")
	public String getHelloMsg() {
		return "Hello World";
	}
	
	/*
	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(
				CarController.class
				));
	}
	*/

}
