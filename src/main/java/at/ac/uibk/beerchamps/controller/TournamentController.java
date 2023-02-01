package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping("/tournament/{id}")
    public String getTournamentView(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        model.addAttribute("tourn", tournament);
        return "tournament-view";
    }

    @PostMapping("/tournament/{id}")
    public String handleTournamentUpdate(@PathVariable("id") Long id, @ModelAttribute Tournament tournament) {
        tournamentService.updateTournament(id, tournament);
        return "redirect:/";
    }

    @GetMapping("/tournament/{id}/delete")
    public String handleTournamentDeletion(@PathVariable("id") Long id) {
        tournamentService.deleteTournament(id);
        return "redirect:/";
    }

    @GetMapping("/tournament/create")
    public String getCreateTournamentView(Model model) {
        model.addAttribute("newTournament", new Tournament());
        return "create-tournament-view";
    }

    @PostMapping("/tournament/create")
    public String handleTournamentCreation(@ModelAttribute Tournament tournament) {
        tournamentService.createTournament(tournament);
        return "redirect:/";
    }

    @PostMapping("/tournament/start")
    public String handleTournamentStart(@ModelAttribute Tournament tournament) {
        tournamentService.generateGames(tournament);
        return "redirect:/";
    }
}