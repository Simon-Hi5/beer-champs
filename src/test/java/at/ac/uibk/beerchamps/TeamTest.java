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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamTest {
    @Mock
    TournamentRepository tournamentRepository;
    @Mock
    TeamRepository teamRepository;
    @InjectMocks
    TournamentService tournamentService = new TournamentServiceImpl();
    @InjectMocks
    TeamService teamService = new TeamServiceImpl();
    Tournament newTournament = new Tournament();
    Player p1,p2,p3,p4;
    HashSet<Player> set1,set2;
    Team t1,t2;
    @BeforeEach
    void init(){
        tournamentService.createTournament(newTournament);

        p1 = new Player("p1");
        p2 = new Player("p2");
        p3 = new Player("p3");
        p4 = new Player("p4");

        set1 = new HashSet<>();
        set2 = new HashSet<>();

        set1.add(p1);
        set1.add(p2);
        set2.add(p3);
        set2.add(p4);

        t1 = new Team(set1,"team1",newTournament);
        t2 = new Team(set2,"team2",newTournament);
    }
    @Test
    void playerCreationTest(){
        Assertions.assertEquals("p1",p1.getName());
        Assertions.assertEquals("p2",p2.getName());
        Assertions.assertEquals("p3",p3.getName());
        Assertions.assertEquals("p4",p4.getName());
    }
    @Test
    void teamCreationTest(){
        Assertions.assertEquals("team1",t1.getTeamName());
        Assertions.assertEquals("team2",t2.getTeamName());
    }

    @Test
    void teamSetIdTest(){
        t1.setId(999);
        t2.setId(12234);

        Assertions.assertEquals(999,t1.getId());
        Assertions.assertEquals(12234,t2.getId());
    }
    @Test
    void teamHashTest(){
        t1.setId(999);
        t2.setId(12234);

        Assertions.assertEquals(Objects.hash(999),t1.hashCode());
        Assertions.assertEquals(Objects.hash(12234),t2.hashCode());
    }
}
