package at.htl.tennis.rest;

import at.htl.tennis.model.Doubles;
import at.htl.tennis.model.Singles;
import at.htl.tennis.model.Tennismatch;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tennismatch")
public class MatchEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAll(){
        List<Tennismatch> matches = em.createNamedQuery("Tennismatch.findAll", Tennismatch.class).getResultList();
        GenericEntity entity = new GenericEntity<List<Tennismatch>>(matches){};
        if (matches != null && !matches.isEmpty()){
            return Response.ok(entity).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Path("{id}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("id") long id){
        try{
            Tennismatch m = em.find(Tennismatch.class, id);
            em.remove(m);
        } catch (Exception e){
            return Response.status(404).build();
        }
        return Response.ok().build();
    }



    @Path("find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Tennismatch find(@PathParam("id") Long id){
        return em.find(Tennismatch.class, id);
    }

    @Path("find/singles/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Singles> findAllSingles(){
        return em.createNamedQuery("Singles.findAll", Singles.class).getResultList();
    }

    @Path("find/doubles/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doubles> findAllDoubles(){
        return em.createNamedQuery("Doubles.findAll", Doubles.class).getResultList();
    }

    @Path("find/singles/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Singles findSingles(@PathParam("id") Long id){
        return em.find(Singles.class, id);
    }

    @Path("find/doubles/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Doubles findDoubles(@PathParam("id") Long id){
        return em.find(Doubles.class, id);
    }

}
