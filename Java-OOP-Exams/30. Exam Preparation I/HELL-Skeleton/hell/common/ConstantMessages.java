package hell.common;

public class ConstantMessages {
    public static final String HERO_CREATED = "Created %s - %s";

    public static final String ITEM_ADDED = "Added item - %s to Hero - %s";

    public static final String RECIPE_ADDED = "Added recipe - %s to Hero - %s";

    public static final String ITEM_INFO = """
            ###Item: %s
            ###+%d Strength
            ###+%d Agility
            ###+%d Intelligence
            ###+%d HitPoints
            ###+%d Damage""";

    public static final String HERO_INSPECT = """
            Hero: %s, Class: %s
            HitPoints: %d, Damage: %d
            Strength: %d
            Agility: %d
            Intelligence: %d
            Items:%s""";

    public static final String HERO_INFO = """
           %s: %s
           ###HitPoints: %d
           ###Damage: %d
           ###Strength: %d
           ###Agility: %d
           ###Intelligence: %d
           ###Items: %s""";
}
