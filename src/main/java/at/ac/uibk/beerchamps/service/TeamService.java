package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Team;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeamService {
    void createTeam(Team team, long tournamentId);

    long updateTeam(long id, Team team);

    void deleteTeam(long teamId);

    Team findTeam(long teamId);

    List<Team> findAllTeamsForTournament(long tournamentId);
}
