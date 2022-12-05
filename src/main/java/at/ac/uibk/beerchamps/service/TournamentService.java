package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament createTournament(Tournament tournament){
        tournamentRepository.save(tournament);
        return(tournament);
    }
    public Tournament editTournament(Tournament tournament){
        tournamentRepository.save(tournament);
        return(tournament);
    }
    public Tournament removeTournament(Tournament tournament){
        tournamentRepository.delete(tournament);
        return(tournament);
    }
    public List<Tournament> getCurrentTournaments(){
        List<Tournament> tournaments = tournamentRepository.findAll();
        return(tournaments);
    }
}
