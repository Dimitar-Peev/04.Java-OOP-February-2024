package core;

import common.Command;
import core.interfaces.Engine;
import core.interfaces.ManagerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private BufferedReader bufferedReader;
    private ManagerController controller;

    public EngineImpl() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.controller = new ManagerControllerImpl();
    }

    @Override
    public void run() {
        String result;

        while (true) {
            try {
                result = processInput();

                if (Command.Exit.name().equals(result)) {
                    break;
                }
            } catch (Exception e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = bufferedReader.readLine();

        String result = null;

        String[] tokens = input.split("\\s+");
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);
        Command command = Command.valueOf(tokens[0]);

        String playerType;
        String username;
        String cardType;
        String cardName;
        switch (command) {
            case AddPlayer:
                playerType = data[0];
                username = data[1];
                result = this.controller.addPlayer(playerType, username);
                break;
            case AddCard:
                cardType = data[0];
                cardName = data[1];
                result = this.controller.addCard(cardType, cardName);
                break;
            case AddPlayerCard:
                username = data[0];
                cardName = data[1];
                result = this.controller.addPlayerCard(username, cardName);
                break;
            case Fight:
                String attacker = data[0];
                String enemy = data[1];
                result = this.controller.fight(attacker, enemy);
                break;
            case Report:
                result = this.controller.report();
                break;
            case Exit:
                result = Command.Exit.name();
        }

        return result;
    }
}
