package at.htl.microservice.health;

import at.htl.microservice.player.boundary.PlayerService;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Liveness
public class PlayerServiceHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    PlayerService playerService;

    @PostConstruct
    public void initClient() {
    }

    @Override
    public HealthCheckResponse call(){
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("TennisClubAdministration Status");
        try{
            playerService.getPlayer();
            responseBuilder.up();
        } catch (Exception e){
            responseBuilder.down();
        }
        return responseBuilder.build();
    }
}
