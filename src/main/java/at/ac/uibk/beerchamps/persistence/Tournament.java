package at.ac.uibk.beerchamps.persistence;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String tournamentName;
    @NotNull
    private String hostName;
    @NotNull
    private TournamentType tournamentType;

    @OneToMany(mappedBy="tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Team> teams;

    @OneToMany(mappedBy="tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Round> rounds;

    public Tournament() {
    }

    public Tournament(String hostName, String tournamentName, TournamentType tournamentType) {
        this.hostName = hostName;
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Tournament)) {
            return false;
        }
        final Tournament other = (Tournament) obj;
        return Objects.equals(getId(), other.getId());
    }
}
