package at.ac.uibk.beerchamps.persistence;

public enum TournamentType {
    ROUNDROBIN("Round Robin"),
    TREESHAPED("Tree-Shaped Tournament");

    public final String label;

    private TournamentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
