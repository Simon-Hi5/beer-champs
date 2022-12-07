package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public long createTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
        return tournament.getId();
    }

    public long updateTournament(long tournamentId, Tournament newTournament) {
        findTournament(tournamentId);
        tournamentRepository.save(newTournament);
        return tournamentId;
    }

    public long deleteTournament(long tournamentId) {
        Tournament tournament = findTournament(tournamentId);
        tournamentRepository.delete(tournament);
        return tournamentId;
    }

    public Tournament findTournament(long tournamentId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            throw new EntityNotFoundException("Tournament with ID=" + tournamentId + " not found.");
        }
        return tournament.get();
    }

    public List<Tournament> findAllTournaments() {
        return tournamentRepository.findAll();
    }
}
