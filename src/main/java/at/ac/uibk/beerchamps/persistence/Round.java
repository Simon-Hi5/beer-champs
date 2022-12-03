package at.ac.uibk.beerchamps.persistence;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Round implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private Set<Team> teams;

    @OneToMany
    private Set<Game> games;

    public Round(Set<Team> teams) {
        this.teams = teams;
    }
    public Round() {}

    @OneToMany
    public Set<Game> getGames() {
        return games;
    }
    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @OneToMany
    public Set<Team> getTeams() {
        return teams;
    }
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return false;
    }
}

