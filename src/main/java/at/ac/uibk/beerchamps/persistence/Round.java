package at.ac.uibk.beerchamps.persistence;

import java.util.List;
import java.util.Set;

public class Round {

    private Set<Team> teams;
    private Set<Game> games;

    public Round(Set<Team> teams) {
        this.teams = teams;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
