package at.ac.uibk.beerchamps.persistence;

public class Game {

    private Team team1;

    private Team team2;

    private Team winner;

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
}
