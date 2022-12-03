package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {

    @Autowired
    TournamentService tournamentService;

    public TeamController() {
    }

    @GetMapping("/create-teams")
    public String getTeamsView(Model model) {
        return "create-teams-view";
    }
}