package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {


    private ModelMapper mapper;

    private TeamRepository teamRepository;

    private PlayerRepository playerRepository;

    public List<TeamDTO> getTeams() {
        return teamRepository.findAll().stream()
                .map(t -> mapper.map(t, TeamDTO.class))
                .toList();
    }

    public TeamDTO CreateNewTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return mapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO AddNewPlayerToExistingTeam(long id, CreatePlayerCommand command) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found team"));

        Player player = new Player(command.getName(), command.getBirthDate(),command.getPosition());

        team.addPlayer(player);

        return mapper.map(team, TeamDTO .class);
    }

    @Transactional
    public TeamDTO AddExistingPlayerWithTeam(long id, UpdateWithExistingPlayerCommand command) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found team"));

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found player"));

        if(playerHasNotTeam(player, team)) {

        team.addPlayer(player); }

        return mapper.map(team, TeamDTO .class);
    }

    private boolean playerHasNotTeam(Player player, Team team){
       if( player.getTeam().equals(getTeams().isEmpty())
               && team.getPlayers().stream()
               .filter(p->p.getPosition()== player.getPosition())
               .toList().size() < 2) {
           return true;
       }
       return false;
    }

}



