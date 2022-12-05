package at.ac.uibk.beerchamps.persistence;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String teamName;

    @OneToMany
    private Set<Player> players;

    public Team() {
    }

    public Team(Set<Player> players, String teamName) {
        this.players = players;
        this.teamName = teamName;
    }


    @OneToMany
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return false;
    }
}
