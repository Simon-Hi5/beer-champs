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

    @Test
    void generateGames(){
        ArgumentCaptor<Tournament> tournamentCaptor = ArgumentCaptor.forClass(Tournament.class);
        Tournament newTournament = new Tournament();
        tournamentService.createTournament(newTournament);

        HashSet<Player> set1 = new HashSet<>();
        HashSet<Player> set2 = new HashSet<>();

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Player p3 = new Player("p3");
        Player p4 = new Player("p4");

        set1.add(p1);
        set1.add(p2);
        set2.add(p3);
        set2.add(p4);

        Team t1 = new Team(set1,"team1",newTournament);
        Team t2 = new Team(set2,"team2",newTournament);
        teamService.createTeam(t1, newTournament.getId());
        teamService.createTeam(t2, newTournament.getId());

        teamRepository.save(t1);
        teamRepository.save(t2);
        tournamentRepository.save(newTournament);

        Assertions.assertEquals(2,newTournament.getTeams().size());
    }

}
