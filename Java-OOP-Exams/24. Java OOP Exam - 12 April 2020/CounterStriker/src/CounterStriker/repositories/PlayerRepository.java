package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static CounterStriker.common.ExceptionMessages.*;

public class PlayerRepository implements Repository<Player> {

    private List<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.models;
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }

        this.models.add(model);
    }

    @Override
    public boolean remove(Player model) {
        return this.getModels().remove(model);
    }

    @Override
    public Player findByName(String name) {
        for (Player player : this.getModels()) {
            if (player.getUsername().equals(name)) {
                return player;
            }
        }

        return null;
    }
}
