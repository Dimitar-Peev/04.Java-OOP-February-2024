package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.*;

import static common.ExceptionMessages.*;

public class PlayerRepositoryImpl implements PlayerRepository {

    private Map<String, Player> players;

    public PlayerRepositoryImpl() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.players.size();
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(this.players.values()));
    }

    @Override
    public void add(Player player) {
        isNotNullPlayer(player);

        String playerName = player.getUsername();
        if (this.players.containsKey(playerName)) {
            throw new IllegalArgumentException(String.format(PLAYER_EXIST, player.getUsername()));
        }

        this.players.put(playerName, player);
    }

    @Override
    public boolean remove(Player player) {
        isNotNullPlayer(player);

        return this.players.remove(player.getUsername()) != null;
    }

    @Override
    public Player find(String name) {
        Player player = this.players.get(name);
        return player;
    }

    private void isNotNullPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException(PLAYER_NULL);
        }
    }
}
