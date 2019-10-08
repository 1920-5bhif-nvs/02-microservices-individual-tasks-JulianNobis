import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")
public class TennisplayerResource {

    @Inject
    @RestClient
    TennisplayerService tennisplayerService;

    @GET
    @Path("/tennisplayer/count")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name="getNumberOfTennisplayers_called")
    @Timed(name="timer", description = "How long it takes to perform this task", unit = MetricUnits.MILLISECONDS)
    public int getNumberOfTennisplayers(){
        return tennisplayerService.getTennisplayer().size();
    }

    @GET
    @Path("/tennisplayer/avg/itn")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getAvgItn_called")
    public double getAvgItn(){
        return tennisplayerService.getTennisplayer().stream().mapToDouble(t -> t.getItn()).average().getAsDouble();
    }

    @GET
    @Path("/tennsisplayer/avg/age")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getAvgAge_called")
    public double getAvgAge(){
        return tennisplayerService.getTennisplayer().stream().mapToDouble(t -> t.getAge()).average().getAsDouble();
    }

}
