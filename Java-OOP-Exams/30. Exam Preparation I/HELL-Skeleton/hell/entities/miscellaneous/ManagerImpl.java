package hell.entities.miscellaneous;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.interfaces.Recipe;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static hell.common.ConstantMessages.*;

public class ManagerImpl implements Manager {

    private Map<String, Hero> localHeroes;

    public ManagerImpl() {
        this.localHeroes = new HashMap<>();
    }

    @Override
    public String addHero(List<String> arguments) {
        String name = arguments.get(1);
        String type = arguments.get(2);

        Hero hero = null;

        switch (type) {
            case "Assassin":
                hero = new Assassin(name);
                break;
            case "Barbarian":
                hero = new Barbarian(name);
                break;
            case "Wizard":
                hero = new Wizard(name);
                break;
        }

        this.localHeroes.put(name, hero);

        return HERO_CREATED.formatted(type, name);
    }

    @Override
    public String addItem(List<String> arguments) {
        String itemName = arguments.get(1);
        String heroName = arguments.get(2);
        int strengthBonus = Integer.parseInt(arguments.get(3));
        int agilityBonus = Integer.parseInt(arguments.get(4));
        int intelligenceBonus = Integer.parseInt(arguments.get(5));
        int hitPoints = Integer.parseInt(arguments.get(6));
        int damage = Integer.parseInt(arguments.get(7));

        Item common = new CommonItem(itemName, strengthBonus, agilityBonus, intelligenceBonus, hitPoints, damage);
        Hero current = this.localHeroes.get(heroName);
        current.addItem(common);

        return ITEM_ADDED.formatted(itemName, heroName);
    }

    @Override
    public String addRecipe(List<String> arguments) {
        String itemName = arguments.get(1);
        String heroName = arguments.get(2);
        int strengthBonus = Integer.parseInt(arguments.get(3));
        int agilityBonus = Integer.parseInt(arguments.get(4));
        int intelligenceBonus = Integer.parseInt(arguments.get(5));
        int hitPoints = Integer.parseInt(arguments.get(6));
        int damage = Integer.parseInt(arguments.get(7));
        List<String> requiredItems = arguments.stream().skip(8).collect(Collectors.toList());

        Recipe recipe = new RecipeItem(itemName, strengthBonus, agilityBonus, intelligenceBonus, hitPoints, damage, requiredItems);

        Hero current = this.localHeroes.get(heroName);
        current.addRecipe(recipe);

        return RECIPE_ADDED.formatted(itemName, heroName);
    }

    @Override
    public String inspect(List<String> arguments) {
        Hero hero = this.localHeroes.get(arguments.get(1));

        return hero.toString();
    }

    @Override
    public String quit() {
        Comparator<Hero> heroComparator =
                Comparator.<Hero>comparingLong(h -> h.getStrength() + h.getAgility() + h.getIntelligence())
                        .thenComparingLong(h -> h.getHitPoints() + h.getDamage())
                        .reversed();

        List<Hero> orderedHeroes = this.localHeroes.values().stream().sorted(heroComparator).toList();

        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (Hero hero : orderedHeroes) {
            Collection<Item> itemCollection = hero.getItems();
            String items = formatItems(itemCollection);

            sb.append(index);
            sb.append(". ");
            sb.append(heroInfo(hero, items));
            sb.append(System.lineSeparator());

            index++;
        }

        return sb.toString().trim();
    }

    private static String formatItems(Collection<Item> items) {
        if (items == null || items.isEmpty()) {
            return "None";
        }

        return items.stream().map(Item::getName).collect(Collectors.joining(", "));
    }

    private static String heroInfo(Hero hero, String itemNames) {
        return HERO_INFO.formatted(
                hero.getClass().getSimpleName(),
                hero.getName(),
                hero.getHitPoints(),
                hero.getDamage(),
                hero.getStrength(),
                hero.getAgility(),
                hero.getIntelligence(),
                itemNames
        );
    }
}

