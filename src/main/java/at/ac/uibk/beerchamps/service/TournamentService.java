package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;

import java.util.Set;

public interface TournamentService {

    Set<Round> createRounds(Set<Team> teams);

}
