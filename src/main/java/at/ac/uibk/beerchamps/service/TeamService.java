package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;

import java.util.List;

public interface TeamService {
    long createTeam(Team team, long tournamentId);

    long updateTeam(long id, Team team);

    long deleteTeam(long teamId);

    Team findTeam(long teamId);

    List<Team> findAllTeamsForTournament(long tournamentId);
}
