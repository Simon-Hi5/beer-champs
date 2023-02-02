package at.ac.uibk.beerchamps;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TournamentRepositoryTest {

    @Autowired
    TournamentRepository tournamentRepository;

    @Test
    void tournamentRepositorySaveAndFindTest() {
        Tournament tournament = new Tournament();
        tournamentRepository.save(tournament);
        Tournament result = tournamentRepository.findAll().get(0);
        assertEquals(tournament, result);
    }
}
