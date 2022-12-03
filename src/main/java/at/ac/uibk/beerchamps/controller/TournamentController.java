package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    public TournamentController() {}

    @GetMapping("/create-tournament")
    public String getCreateTournamentView(Model model) {
        model.addAttribute("newTournament", new Tournament());
        return "create-tournament-view";
    }

    @PostMapping("/create-tournament")
    public String handleTournamentCreation(@ModelAttribute Tournament tournament){
        Tournament newTournament = tournamentService.createTournament(tournament);
        if(newTournament != null)
            return "redirect:/create-teams";
        else
            return "redirect:/create-tournament?error";
    }
}
