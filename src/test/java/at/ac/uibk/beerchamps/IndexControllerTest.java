package at.ac.uibk.beerchamps;

import at.ac.uibk.beerchamps.service.GameService;
import at.ac.uibk.beerchamps.service.RoundService;
import at.ac.uibk.beerchamps.service.TeamService;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest
class IndexControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    TournamentService tournamentService;

    @MockBean
    RoundService roundService;

    @MockBean
    GameService gameService;

    @MockBean
    TeamService teamService;

    @Test
    void testGetIndex() throws Exception {
        MvcResult indexPage = mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertTrue(indexPage.getResponse().getContentAsString().contains("BeerChamps"));
    }
}
