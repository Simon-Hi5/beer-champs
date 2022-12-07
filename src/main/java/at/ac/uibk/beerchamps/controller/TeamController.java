package at.ac.uibk.beerchamps.controller;

import at.ac.uibk.beerchamps.persistence.Team;
import at.ac.uibk.beerchamps.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/tournament/{id}/team/create")
    public String getCreateTeamView(Model model, @PathVariable("id") Long tournamentId) {
        model.addAttribute("newTeam", new Team());
        model.addAttribute("tournID", tournamentId);
        return "create-team-view";
    }

    @PostMapping("/tournament/{id}/team/create")
    public String handleTeamCreation(@ModelAttribute Team team, @PathVariable("id") Long tournamentId) {
        teamService.createTeam(team, tournamentId);
        return "redirect:/tournament/" + tournamentId;
    }

    @GetMapping("/tournament/{id}/team/{teamId}/delete")
    public String handleTournamentDeletion(@PathVariable String id, @PathVariable("teamId") Long teamId) {
        teamService.deleteTeam(teamId);
        return "redirect:/tournament/" + id;
    }
}
