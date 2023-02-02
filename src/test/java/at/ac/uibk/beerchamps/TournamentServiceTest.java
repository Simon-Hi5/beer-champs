package at.ac.uibk.beerchamps;

import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.persistence.TournamentType;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    TournamentRepository tournamentRepository;

    @InjectMocks
    TournamentService tournamentService = new TournamentServiceImpl();

    @Test
    void createTournament() {
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

    @Test
    void addTeamsToTournament() {
        ArgumentCaptor<Tournament> tournamentCaptor = ArgumentCaptor.forClass(Tournament.class);

        Tournament t = new Tournament();
        Team t1 = new Team();
        t1.setTeamName("Team1");
        Team t2 = new Team();
        t2.setTeamName("Team2");
        List<Team> teamList = new ArrayList<>();
        teamList.add(t1);
        teamList.add(t2);
        t.setTeams(teamList);

        tournamentService.createTournament(t);
        Mockito.verify(tournamentRepository).save(tournamentCaptor.capture());

        Assertions.assertEquals(t1, tournamentCaptor.getValue().getTeams().get(0));
        Assertions.assertEquals(t2, tournamentCaptor.getValue().getTeams().get(1));
    }

    @Test
    void addNameToTournament() {
        ArgumentCaptor<Tournament> tournamentCaptor = ArgumentCaptor.forClass(Tournament.class);

        String tournamentName = "Tournament Name";
        String hostName = "Host Name";

        Tournament t = new Tournament();
        t.setTournamentName(tournamentName);
        t.setTournamentType(TournamentType.ROUNDROBIN);
        t.setHostName(hostName);

        tournamentService.createTournament(t);
        Mockito.verify(tournamentRepository).save(tournamentCaptor.capture());

        Assertions.assertEquals(tournamentName, tournamentCaptor.getValue().getTournamentName());
        Assertions.assertEquals(hostName, tournamentCaptor.getValue().getHostName());
    }

}
