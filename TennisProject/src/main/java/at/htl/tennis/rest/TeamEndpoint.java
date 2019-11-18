package at.htl.tennis.rest;

import at.htl.tennis.model.Team;
import at.htl.tennis.model.Tennismatch;
import at.htl.tennis.model.Tennisplayer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("team")
public class TeamEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<Team> teams = em.createNamedQuery("Team.findAll", Team.class).getResultList();
        if (teams == null || teams.size() == 0){
            return Response.noContent().build();
        }
        return Response.ok(teams).build();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") long id){
        Team team = em.find(Team.class, id);
        if(team == null){
            return Response.noContent().build();
        }
        return Response.ok(team).build();
    }

    @Path("{id}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("id") long id){
        try{
            Team t = em.find(Team.class, id);
            if (t != null){
                List<Tennisplayer> teamPlayers = em.createQuery("select t from Tennisplayer t where t.team = :arg", Tennisplayer.class).setParameter("arg", t).getResultList();
                for (Tennisplayer player : teamPlayers){
                    player.setTeam(null);
                }
                em.remove(t);
            }
        } catch(Exception e){
            return Response.status(404).build();
        }
        return Response.ok().build();
    }

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTeam(Team team){
        em.persist(team);
        em.flush();
        return Response.created(URI.create("http://localhost:8080/api/team/find/" + team.getId())).build();
    }

}
