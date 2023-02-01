package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.*;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

    @GetMapping("/tournament/{id}/start")
    public String startTournament(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        tournament = tournamentService.generateGames(tournament);
        System.out.println(tournament.getRounds().size());
        return "redirect:/tournament/"+id+"/currentRound";
    }
    @GetMapping("/tournament/{id}/currentRound")
    public String getCurrentRound(@PathVariable("id") Long id, Model model) {
        Tournament tournament = tournamentService.findTournament(id);
        model.addAttribute("tourn", tournament);
        System.out.println(tournament.getLastRound());
        return "tournament-round";
    }

    @PostMapping("/tournament/{id}")
    public String handleTournamentUpdate(@PathVariable("id") Long id, @ModelAttribute Tournament tournament) {
        tournamentService.updateTournament(id, tournament);
        return "redirect:/";
    }

    @PostMapping("/tournament/{id}/game/{game_id}/set-winner")
    public String handleRoundSave(@PathVariable("id") Long tourn_id, @PathVariable("game_id") Long game_id, @RequestParam("winnerId") Long winnerId, Model model) {
        Tournament tournament = tournamentService.findTournament(tourn_id);
        tournamentService.setWinner(tournament, game_id, winnerId);
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