package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class TeamServiceImpl implements TeamService  {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TournamentService tournamentService;

    public long createTeam(Team team, long tournamentId) {
        Tournament tournament = tournamentService.findTournament(tournamentId);
        team.setTournament(tournament);
        teamRepository.save(team);
        return team.getId();
    }

    public long updateTeam(long teamId, Team newTeam) {
        findTeam(teamId);
        teamRepository.save(newTeam);
        return teamId;
    }

    public long deleteTeam(long teamId) {
        Team tournament = findTeam(teamId);
        teamRepository.delete(tournament);
        return teamId;
    }

    public Team findTeam(long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new EntityNotFoundException("Team with ID=" + teamId + " not found.");
        }
        return team.get();
    }

    public List<Team> findAllTeamsForTournament(long tournamentId) {
        Tournament tournament = tournamentService.findTournament(tournamentId);
        return teamRepository.findAllByTournament(tournament);
    }
}
