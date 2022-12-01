package at.ac.uibk.beerchamps.persistence;

import javax.persistence.Entity;
import java.util.Set;


public class Tournament {

    private String hostName;

    private String tournamentName;

    private TournamentType tournamentType;

    private Set<Team> teams;


    private Set<Round> rounds;

    public Tournament() {
    }

    public Tournament(String hostName, String tournamentName, TournamentType tournamentType, int amountTables) {
        this.hostName = hostName;
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }

    public void addRound(Round round) {
        this.rounds.add(round);
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}
