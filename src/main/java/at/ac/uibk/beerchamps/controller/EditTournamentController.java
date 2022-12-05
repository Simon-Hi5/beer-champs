package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.Tournament;
import at.ac.uibk.beerchamps.repository.TournamentRepository;
import at.ac.uibk.beerchamps.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class EditTournamentController {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    TournamentRepository tournamentRepository;

    public EditTournamentController() {
    }

    @GetMapping("/edit-tournament/{id}")
    public String getEditTournamentView(@PathVariable("id") Long id, Model model) {
        if (!tournamentRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        model.addAttribute("tourn", tournament.get());
        return "edit-tournament-view";
    }

    @PostMapping("/edit-tournament/{id}")
    public String handleTournamentCreation(@ModelAttribute Tournament tournament) {
        Tournament newTournament = tournamentService.editTournament(tournament);
        return "redirect:/edit-tournament/" + tournament.getId() + "?success";
    }

    @GetMapping("/remove-tournament/{id}")
    public String getEditTournamentView(@PathVariable("id") Long id) {
        if (!tournamentRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        tournamentService.removeTournament(tournament.get());
        return "redirect:/";
    }
}