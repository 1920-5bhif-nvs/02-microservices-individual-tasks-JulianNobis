package at.htl.microservice.player.boundary;


import at.htl.microservice.player.entity.Player;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/player")
@RegisterRestClient
public interface PlayerService {

    @GET
    @Produces("application/json")
    List<Player> getPlayer();
}