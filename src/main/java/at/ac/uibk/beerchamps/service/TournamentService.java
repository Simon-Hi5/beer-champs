package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TournamentService {

    @Autowired
    private TournamentRepository repository;

    public Tournament createTournament(Tournament tournament){
        return(repository.save(tournament));
    }
}
