package models.players;

import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

import static common.ExceptionMessages.*;

public abstract class BasePlayer implements Player {
    private static final int MIN_HEALTH_POINTS = 0;

    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository, String username, int health) {
        this.cardRepository = cardRepository;
        this.setUsername(username);
        this.setHealth(health);
        this.isDead = false;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_NULL_OR_EMPTY);
        }
        this.username = username;
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < MIN_HEALTH_POINTS) {
            throw new IllegalArgumentException(PLAYER_HEALTH_LESS_THAN_ZERO);
        }
        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < 0) {
            throw new IllegalArgumentException(DAMAGE_LESS_THAN_ZERO);
        }

        this.health -= damagePoints;

        if (this.health < MIN_HEALTH_POINTS) {
            this.health = 0;
            this.isDead = true;
        }
    }

}
