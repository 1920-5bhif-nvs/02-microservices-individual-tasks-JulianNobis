package at.htl.tennis.business;

import at.htl.tennis.model.*;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;

@ApplicationScoped
public class InitBean {
    @Inject
    EntityManager em;

    @Transactional
    void init(@Observes StartupEvent ev){

        Team team = new Team("Landesliga C");
        Tennisplayer julianNobis = new TournamentPlayer("Julian Nobis", 5.0, 2001, GenderPlayers.Gender.MALE,0,0,  000001);
        em.persist(julianNobis);
        Tennisplayer yannikLeitner = new TournamentPlayer("Yannik Leitner", 3.5, 1992, GenderPlayers.Gender.MALE, 0, 0, 000002);
        em.persist(yannikLeitner);
        Tennisplayer danGroza = new TournamentPlayer("Dan Groza", 2.4, 1973, GenderPlayers.Gender.MALE, 0, 0, 000003);
        em.persist(danGroza);
        Tennisplayer philippBräuer = new TournamentPlayer("Philipp Bräuer", 4.4, 1991, GenderPlayers.Gender.MALE, 0, 0, 000004);
        em.persist(philippBräuer);
        Tennisplayer soranaGroza = new HobbyPlayer("Sorana Groza", 9.5, 2004, GenderPlayers.Gender.FEMALE, 0, 0, true);
        em.persist(soranaGroza);
        Tennisplayer sofiaGroza = new HobbyPlayer("Sofia Groza", 8.8, 2002, GenderPlayers.Gender.FEMALE, 0, 0, true);
        em.persist(sofiaGroza);
        Tennisplayer lukasStransky = new HobbyPlayer("Lukas Stransky", 8.0, 2000, GenderPlayers.Gender.MALE, 0, 0, false);
        em.persist(lukasStransky);


        team.addTeamMember(julianNobis);
        team.addTeamMember(danGroza);
        team.addTeamMember(philippBräuer);
        team.addTeamMember(yannikLeitner);

        em.persist(team);

        Tennismatch firstSingle = new Singles(LocalDate.now(), 140, "6:4 6:7 6:0", julianNobis, philippBräuer, julianNobis);
        Tennismatch secondSingle = new Singles(LocalDate.now(), 70, "6:4 6:1", danGroza, yannikLeitner, danGroza);
        Tennismatch thirdSingle = new Singles(LocalDate.now(), 100, "6:2 6:4", soranaGroza, sofiaGroza, sofiaGroza);


        em.persist(firstSingle);
        em.persist(secondSingle);
        em.persist(thirdSingle);

        Tennismatch firstDouble = new Doubles(LocalDate.now(), 80, "6:4 6:3", julianNobis, danGroza, yannikLeitner, philippBräuer, julianNobis, danGroza);
        Tennismatch secondDouble = new Doubles(LocalDate.now(), 110, "7:5 6:7 10:8", sofiaGroza, philippBräuer, soranaGroza, danGroza, soranaGroza, danGroza);

        em.persist(firstDouble);
        em.persist(secondDouble);

    }
}
