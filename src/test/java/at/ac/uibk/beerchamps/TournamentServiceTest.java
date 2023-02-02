package at.ac.uibk.beerchamps;

import at.ac.uibk.beerchamps.persistence.Player;
import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TeamRepository;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import at.ac.uibk.beerchamps.service.TeamService;
import at.ac.uibk.beerchamps.service.TeamServiceImpl;
import at.ac.uibk.beerchamps.service.TournamentService;
import at.ac.uibk.beerchamps.service.TournamentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    TournamentRepository tournamentRepository;
    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TournamentService tournamentService = new TournamentServiceImpl();

    @InjectMocks
    TeamService teamService = new TeamServiceImpl();
    @Test
    void createTournamentSuccess() {
        ArgumentCaptor<Tournament> tournamentCaptor = ArgumentCaptor.forClass(Tournament.class);
        Tournament newTournament = new Tournament();
        tournamentService.createTournament(newTournament);
        Mockito.verify(tournamentRepository).save(tournamentCaptor.capture());
        Assertions.assertEquals(newTournament, tournamentCaptor.getValue());
    }

    @Test
    void findTournamentException() {
        assertThrows(EntityNotFoundException.class, () -> {
            tournamentService.findTournament(5L);
        });
    }


}
