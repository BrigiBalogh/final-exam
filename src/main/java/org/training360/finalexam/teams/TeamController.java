package org.training360.finalexam.teams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Operations on team")
public class TeamController {


    private final TeamService teamService;


    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping
    public List<TeamDTO> getTeams() {
        return teamService.getTeams();
    }

    @PostMapping
    @Operation(summary = "Creates a team", description = " New team has been created.")
    @ApiResponse(responseCode = "201", description = "Team is  found")
    public TeamDTO CreateNewTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.CreateNewTeam(command);
    }

    @PostMapping("/{id}/players")
    public TeamDTO AddNewPlayerToExistingTeam(@PathVariable("id") long id, @Valid @RequestBody CreatePlayerCommand command){
        return teamService.AddNewPlayerToExistingTeam(id, command);

    }


    @PutMapping("/{id}/players")
    public TeamDTO AddExistingPlayerToTeam(@PathVariable("id") long id, @Valid @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.AddExistingPlayerToTeam(id,command);
    }


   /* @PutMapping("/{id}/players")
    public TeamDTO getFreeAgentPlayer(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command){
        return teamService.addNewFreeAgentToTeam(id, command);
    }*/

}
