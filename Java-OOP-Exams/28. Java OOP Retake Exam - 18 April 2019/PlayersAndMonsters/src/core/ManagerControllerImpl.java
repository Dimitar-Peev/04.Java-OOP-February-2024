package core;

import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.MagicCard;
import models.cards.TrapCard;
import models.cards.interfaces.Card;
import models.players.Advanced;
import models.players.Beginner;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import java.util.List;

import static common.ConstantMessages.*;

public class ManagerControllerImpl implements ManagerController {

    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
        this.cardRepository = new CardRepositoryImpl();
        this.playerRepository = new PlayerRepositoryImpl();
        this.battlefield = new BattleFieldImpl();
    }

    public String addPlayer(String type, String username) {

        Player player = null;
        if ("Beginner".equals(type)) {
            CardRepositoryImpl cardRepository1 = new CardRepositoryImpl();
            player = new Beginner(cardRepository1, username);
        } else if (Advanced.class.getSimpleName().equals(type)) {
            player = new Advanced(new CardRepositoryImpl(), username);
        }

        this.playerRepository.add(player);
        return String.format(SUCCESSFULLY_ADDED_PLAYER, type, username);
    }

    @Override
    public String addCard(String type, String name) {

        Card card = null;
        if ("Magic".equals(type)) {
            card = new MagicCard(name);
        } else if ("Trap".equals(type)) {
            card = new TrapCard(name);
        }

        this.cardRepository.add(card);
        return String.format(SUCCESSFULLY_ADDED_CARD, type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        Card card = this.cardRepository.find(cardName);
        Player player = this.playerRepository.find(username);

        player.getCardRepository().add(card);
        return String.format(SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS, cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attacker = this.playerRepository.find(attackUser);
        Player enemy = this.playerRepository.find(enemyUser);

        this.battlefield.fight(attacker, enemy);

        return String.format(FIGHT_INFO, attacker.getHealth(), enemy.getHealth());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        List<Player> players = this.playerRepository.getPlayers();

        for (Player player : players) {
            sb.append(player);
            sb.append(System.lineSeparator());

            CardRepository cardRepository = player.getCardRepository();
            for (Card card : cardRepository.getCards()) {
                sb.append(card).append(System.lineSeparator());
            }

            sb.append(DEFAULT_REPORT_SEPARATOR);
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
