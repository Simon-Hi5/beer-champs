package at.ac.uibk.beerchamps.persistence;

import com.sun.istack.NotNull;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Tournament implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull
    private String tournamentName;
    @NotNull
    private String hostName;
    @NotNull
    private TournamentType tournamentType;

    @OneToMany(mappedBy="tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Team> teams;

    @OneToMany
    private Set<Round> rounds;

    public Tournament() {
    }

    public Tournament(String hostName, String tournamentName, TournamentType tournamentType, int amountTables) {
        this.hostName = hostName;
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
    }

    @OneToMany
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @OneToMany
    public Set<Round> getRounds() {
        return rounds;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
