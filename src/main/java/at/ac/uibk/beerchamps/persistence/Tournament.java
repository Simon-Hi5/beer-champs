package at.ac.uibk.beerchamps.persistence;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Tournament {

    private String hostName;

    private String tournamentName;

    private Set<Round> rounds;

    public Tournament(String hostName, String tournamentName) {
        this.hostName = hostName;
        this.tournamentName = tournamentName;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }
}
