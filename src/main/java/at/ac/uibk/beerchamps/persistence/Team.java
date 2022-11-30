package at.ac.uibk.beerchamps.persistence;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Team {

    private Set<Player> players;

    private String teamName;

    public Team(Set<Player> players, String teamName) {
        this.players = players;
        this.teamName = teamName;
    }

}
