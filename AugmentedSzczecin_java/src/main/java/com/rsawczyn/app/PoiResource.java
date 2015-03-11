package com.rsawczyn.app;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/pois")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {
	
	private List<Poi> pois;
	private long uniqueId;
	
	public PoiResource() {
		this.pois = new ArrayList<Poi>();
		uniqueId = 0;
	}
	
	@GET
	public List<Poi> displayListOfPoints() {
		return pois;
    }
	
	@POST
	@Path("/{name}/{cat}")
	public String createPoi(@PathParam("name") String name, @PathParam("cat") String category) {
		++uniqueId;
		System.out.println("Category: " + category);
        pois.add(new Poi(uniqueId, name, category));
		return "Added point named \"" + name + "\" to list of points!";
    }
	
	@GET
	@Path("/{id}")
	public Poi retrievePoi(@PathParam("id") int id) {
		for(Poi poi : pois) {
			if(poi.getId() == id)
				return poi;
		}
		return null;
    }
	
	@PUT
	@Path("/{id}/{name}/{cat}")
	public String updatePoi(@PathParam("id") int id) {
		for(Poi poi : pois) {
			if(poi.getId() == id)
				return "Updated point - id: " + id + " ,name: " + poi.getName();
		}
		return "Point with id: " + id + " doesnt exist!";
    }
	
	@DELETE
	@Path("/{id}")
	public String deletePoi(@PathParam("id") int id) {
		for(Poi poi : pois) {
			if(poi.getId() == id) {
				pois.remove(poi);
				return "Deleted point - id: " + id + " ,name: " + poi.getName();
			}			
		}
		return "Point with id: " + id + "doesnt exist!";
    }
}
