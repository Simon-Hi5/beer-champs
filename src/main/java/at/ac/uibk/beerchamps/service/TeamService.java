package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Team;

import java.util.Set;

public interface TeamService {

    public Team createTeam(Team team, Long tournamentID);

    public Team removeTeam(Team team, Long tournamentID);

    public Set<Team> getCurrentTeams(Long tournamentID);
}
