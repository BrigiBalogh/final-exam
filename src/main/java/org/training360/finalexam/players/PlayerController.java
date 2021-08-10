package org.training360.finalexam.players;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Operations on guest")
public class PlayerController {


    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> listPlayers() {
        return playerService.listPlayers();
    }


    @PostMapping
    @Operation(summary = "Creates a player", description = " New player has been created.")
    @ApiResponse(responseCode = "201", description = "Player is  found")
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }


    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") long id) {
        playerService.deletePlayer(id);
    }
}
