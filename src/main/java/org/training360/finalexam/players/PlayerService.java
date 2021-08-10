package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private ModelMapper mapper;

    private PlayerRepository playerRepository;

    public List<PlayerDTO> listPlayers() {

        return playerRepository.findAll().stream()
                .map(p -> mapper.map(p, PlayerDTO.class))
                .toList();
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {

        Player player = new Player(command.getName(), command.getBirthDate(),
                command.getPosition());
        playerRepository.save(player);
        return mapper.map(player, PlayerDTO.class);
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

}
