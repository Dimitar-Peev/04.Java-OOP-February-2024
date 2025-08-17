package models.battleFields;

import common.ExceptionMessages;
import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.Beginner;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {
    private static final int BEGINNER_INCREASE_HEALTH = 40;
    private static final int BEGINNER_INCREASE_POINTS = 30;

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_DEAD);
        }

        preFightPreparation(attackPlayer);
        preFightPreparation(enemyPlayer);

        getHealthPointsFromDeck(attackPlayer);
        getHealthPointsFromDeck(enemyPlayer);

        startBattle(attackPlayer, enemyPlayer);
    }

    private void startBattle(Player attackPlayer, Player enemyPlayer) {
        int attackPlayerDamage = getDamagePoints(attackPlayer);
        int enemyPlayerDamage = getDamagePoints(enemyPlayer);

        while (!enemyPlayer.isDead() && !attackPlayer.isDead()) {
            enemyPlayer.takeDamage(attackPlayerDamage);

            if (enemyPlayer.isDead()) {
                continue;
            }

            attackPlayer.takeDamage(enemyPlayerDamage);
        }
    }

    private void getHealthPointsFromDeck(Player player) {
        int healthPoints = player.getCardRepository().getCards().stream().mapToInt(Card::getHealthPoints).sum();

        player.setHealth(player.getHealth() + healthPoints);
    }

    private void preFightPreparation(Player player) {
        if (!Beginner.class.getSimpleName().equals(player.getClass().getSimpleName())) {
            return;
        }

        player.setHealth(player.getHealth() + BEGINNER_INCREASE_HEALTH);
        player
                .getCardRepository()
                .getCards()
                .forEach(card -> card.setDamagePoints(card.getDamagePoints() + BEGINNER_INCREASE_POINTS));
    }

    private int getDamagePoints(Player player) {
        return player.getCardRepository().getCards().stream().mapToInt(Card::getDamagePoints).sum();
    }
}
