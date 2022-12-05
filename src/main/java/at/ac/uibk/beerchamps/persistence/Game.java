package at.ac.uibk.beerchamps.persistence;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class Game implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Team team1;
    @OneToOne
    private Team team2;
    @OneToOne
    private Team winner;

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Game() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne
    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    @OneToOne
    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    @OneToOne
    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    @Override
    @Transient
    public boolean isNew() {
        return false;
    }
}
