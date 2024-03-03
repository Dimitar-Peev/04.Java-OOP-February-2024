package _02_Exercise._05_FootballTeamGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Team> teams = new HashMap<>();

        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] inputArr = input.split(";");
            String command = inputArr[0];
            String teamName = inputArr[1];

            switch (command) {
                case "Team":
                    Team team = new Team(teamName);
                    teams.putIfAbsent(teamName, team);
                    break;

                case "Add":
                    String playerName = inputArr[2];
                    int endurance = Integer.parseInt(inputArr[3]);
                    int sprint = Integer.parseInt(inputArr[4]);
                    int dribble = Integer.parseInt(inputArr[5]);
                    int passing = Integer.parseInt(inputArr[6]);
                    int shooting = Integer.parseInt(inputArr[7]);

                    if (!teams.containsKey(teamName)) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        try {
                            teams.get(teamName).addPlayer
                                    (new Player(playerName, endurance, sprint, dribble, passing, shooting));
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    break;

                case "Remove":
                    playerName = inputArr[2];
                        teams.get(teamName).removePlayer(playerName);

                    break;

                case "Rating":
                    if (!teams.containsKey(teamName)) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        System.out.printf("%s - %.0f%n", teamName, (teams.get(teamName).getRating()));
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
