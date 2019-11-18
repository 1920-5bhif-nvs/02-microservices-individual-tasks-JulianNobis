package at.htl.tennis.rest;

import at.htl.tennis.model.Team;
import at.htl.tennis.model.Tennismatch;
import at.htl.tennis.model.Tennisplayer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/player")
public class PlayerEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findAll(){
        return Response.ok(em.createNamedQuery("Tennisplayer.findAll", Tennisplayer.class).getResultList()).build();
    }

    @Path("{id}")
    @GET
    @Transactional
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("id") Long id){
        Tennisplayer tennisplayer = em.find(Tennisplayer.class, id);
        if (tennisplayer != null){
            return Response.ok(tennisplayer).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Path("bestplayer")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBestPlayer(){
        Tennisplayer tennisplayer = em.createNamedQuery("Tennisplayer.getBestPlayer", Tennisplayer.class).getSingleResult();
        if (tennisplayer != null){
            return Response.ok(tennisplayer).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Path("delete/{id}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("id") Long id){
        try{
            Tennisplayer tennisplayer = em.find(Tennisplayer.class, id);
            List<Team> teams = em.createNamedQuery("Team.findAll", Team.class).getResultList();
            for (Team team : teams){
                team.removeTeamMember(tennisplayer);
            }
            em.remove(tennisplayer);
        } catch (Exception e){
            return Response.status(404).build();
        }
        return Response.ok().build();

    }

}