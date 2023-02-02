package at.ac.uibk.beerchamps;

import at.ac.uibk.beerchamps.persistence.Game;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTest {
    @Mock
    TournamentRepository tournamentRepository;
    @Mock
    TeamRepository teamRepository;
    @InjectMocks
    TournamentService tournamentService = new TournamentServiceImpl();
    @InjectMocks
    TeamService teamService = new TeamServiceImpl();
    Tournament newTournament = new Tournament();
    Player p1,p2,p3,p4,p5,p6,p7,p8;
    HashSet<Player> set1,set2,set3,set4;
    Team t1,t2,t3,t4;

    Game g1,g2;
    @BeforeEach
    void init(){
        tournamentService.createTournament(newTournament);

        p1 = new Player("p1");
        p2 = new Player("p2");
        p3 = new Player("p3");
        p4 = new Player("p4");
        p5 = new Player("p5");
        p6 = new Player("p6");
        p7 = new Player("p7");
        p8 = new Player("p8");

        set1 = new HashSet<>();
        set2 = new HashSet<>();
        set3 = new HashSet<>();
        set4 = new HashSet<>();

        set1.add(p1);
        set1.add(p2);
        set2.add(p3);
        set2.add(p4);
        set3.add(p5);
        set3.add(p6);
        set4.add(p7);
        set4.add(p8);

        t1 = new Team(set1,"team1",newTournament);
        t2 = new Team(set2,"team2",newTournament);
        t3 = new Team(set3,"team3",newTournament);
        t4 = new Team(set4,"team4",newTournament);


        g1 = new Game();
        g2 = new Game();
    }
    @Test
    void setTeamsTest(){
        Assertions.assertEquals(null,g1.getTeam1());

        g1.setTeam1(t1);
        g1.setTeam2(t2);

        Assertions.assertEquals(t1,g1.getTeam1());
        Assertions.assertEquals(t2,g1.getTeam2());

    }
    @Test
    void setWinnerTest(){
        Assertions.assertEquals(null,g1.getWinner());

        g1.setWinner(t2);

        Assertions.assertEquals(t2, g1.getWinner());

    }

    @Test
    void gameHashTest(){

        g1.setId(999);
        g2.setId(12234);

        Assertions.assertEquals(Objects.hash(999),g1.hashCode());
        Assertions.assertEquals(Objects.hash(12234),g2.hashCode());
    }
}
