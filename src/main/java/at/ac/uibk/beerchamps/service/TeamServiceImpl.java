package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TeamRepository;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Component
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TournamentRepository tournamentRepository;

    public Team createTeam(Team team, Long tournamentID) {
        Tournament tournament = tournamentRepository.findById(tournamentID).get();
        team.setTournament(tournament);
        teamRepository.save(team);
        return (team);
    }

    public Team removeTeam(Team team, Long tournamentID) {
        Tournament tournament = tournamentRepository.findById(tournamentID).get();
        Set<Team> teams = tournament.getTeams();
        teams.remove(team);
        tournament.setTeams(teams);
        tournamentRepository.save(tournament);
        return (team);
    }

    public Set<Team> getCurrentTeams(Long tournamentID) {
        Tournament tournament = tournamentRepository.findById(tournamentID).get();
        Set<Team> teams = tournament.getTeams();
        return (teams);
    }
}
