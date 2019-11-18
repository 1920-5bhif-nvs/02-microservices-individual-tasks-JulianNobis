package at.htl.microservice.player.boundary;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Period;

@Path("/api")
public class PlayerResource {

    @Inject
    @RestClient
    PlayerService playerService;

    @GET
    @Path("/player/count")
    @Produces(MediaType.TEXT_PLAIN)
    @Timeout(3000)
    @Retry(maxRetries = 3)
    @Fallback(fallbackMethod = "fallbackPlayerCount")
    @Counted(name = "getPlayersCount_called" )
    @Timed(name= "timer", description = "How long it takes to perform this task", unit = MetricUnits.MILLISECONDS)
    public int getPlayersCount() {
        return playerService.getPlayer().size();
    }

    int fallbackPlayerCount(){
        return 0;
    }

    @GET
    @Path("/player/avg/itn")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getAvgItn_called" )
    public double getAvgItn(){
        return playerService.getPlayer().stream().mapToDouble(p -> p.getItn()).average().getAsDouble();
    }

    @GET
    @Path("/player/avg/age")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getAvgAge_called" )
    public double getAvgAge(){
        return playerService.getPlayer().stream().mapToDouble(p -> p.getAge()).average().getAsDouble();
    }
}