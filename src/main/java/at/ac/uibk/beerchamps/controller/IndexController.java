package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    TournamentService tournamentService;

    public IndexController() {}

    @GetMapping("/")
    public String getIndexView(Model m) {
        List<Tournament> currentTournaments = tournamentService.getCurrentTournaments();
        m.addAttribute("currentTournaments", currentTournaments);
        return "index";
    }
}
