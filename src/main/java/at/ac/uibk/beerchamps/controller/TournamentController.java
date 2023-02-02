package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.Round;
import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping("/tournament/{id}")
    public String getTournamentView(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        model.addAttribute("tourn", tournament);
        String location = "tournament-view";
        if(tournament.getRounds().size() > 0)
            location = "tournament-round";
        return location;
    }

    @GetMapping("/tournament/{id}/start")
    public String startTournament(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        tournamentService.generateGames(tournament);
        return "redirect:/tournament/" + id + "/currentRound";
    }

    @GetMapping("/tournament/{id}/currentRound")
    public String getCurrentRound(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        model.addAttribute("tourn", tournament);
        return "tournament-round";
    }

    @PostMapping("/tournament/{id}")
    public String handleTournamentUpdate(@PathVariable("id") Long id, @ModelAttribute Tournament tournament) {
        tournamentService.updateTournament(id, tournament);
        return "redirect:/";
    }

    @PostMapping("/tournament/{id}/game/{game_id}/set-winner")
    public String handleRoundSave(@PathVariable("id") Long tournId, @PathVariable("game_id") Long gameId, @RequestParam("winnerId") Long winnerId, Model model) {
        Tournament tournament = tournamentService.findTournament(tournId);
        tournamentService.setWinner(tournament, gameId, winnerId);
        model.addAttribute("tourn", tournament);
        return "redirect:/tournament/{id}/currentRound";
    }

    @GetMapping("/tournament/{id}/delete")
    public String handleTournamentDeletion(@PathVariable("id") Long id) {
        tournamentService.deleteTournament(id);
        return "redirect:/";
    }

    @GetMapping("/tournament/{id}/scoreboard")
    public String showTournamentScoreboard(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        Round round = tournament.getLastRound();
        model.addAttribute("tourn", tournament);
        model.addAttribute("scoreboard", tournamentService.generateScoreboard(round));
        return "tournament-scoreboard";
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