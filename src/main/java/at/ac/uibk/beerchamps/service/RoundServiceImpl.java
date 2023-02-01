package at.ac.uibk.beerchamps.service;

import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.RoundRepository;
import at.ac.uibk.beerchamps.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class RoundServiceImpl implements RoundService{

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private TournamentService tournamentService;


    public long createRound(Round round, long tournamentId) {
        Tournament tournament = tournamentService.findTournament(tournamentId);
        round.setTournament(tournament);
        roundRepository.save(round);
        return round.getId();
    }

    @Override
    public long updateRound(long roundId, Round newRound) {
        findRound(roundId);
        roundRepository.save(newRound);
        return roundId;
    }

    @Override
    public long deleteRound(long roundId) {
        Round round = findRound(roundId);
        roundRepository.delete(round);
        return roundId;
    }

    @Override
    public Round findRound(long roundId) {
        Optional<Round> round = roundRepository.findById(roundId);
        if (round.isEmpty()) {
            throw new EntityNotFoundException("Team with ID=" + roundId + " not found.");
        }
        return round.get();
    }

    @Override
    public List<Round> findAllRoundsForTournament(long tournamentId) {
        Tournament tournament = tournamentService.findTournament(tournamentId);
        return roundRepository.findAllByTournament(tournament);
    }
}
