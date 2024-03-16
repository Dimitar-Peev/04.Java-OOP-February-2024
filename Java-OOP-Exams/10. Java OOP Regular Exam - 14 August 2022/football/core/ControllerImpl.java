package football.core;

import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
    private Map<String, Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {

        Field field = null;

        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(INVALID_FIELD_TYPE);
        }

        fields.put(fieldName, field);

        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {

        Supplement supplementType = null;

        switch (type) {
            case "Powdered":
                supplementType = new Powdered();
                break;
            case "Liquid":
                supplementType = new Liquid();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        supplement.add(supplementType);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {

        Supplement supplement1 = supplement.findByType(supplementType);

        if (supplement1 == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = fields.get(fieldName);

        field.addSupplement(supplement1);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {

        Player p = null;

        switch (playerType) {
            case "Women":
                p = new Women(playerName, nationality, strength);
                break;
            case "Men":
                p = new Men(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }


        Field field = fields.get(fieldName);

        String output = null;

        if (!playerCanPlay(playerType, field)) {
            output = FIELD_NOT_SUITABLE;
        } else {
            field.addPlayer(p);
            output = String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        }

        return output;
    }

    private boolean playerCanPlay(String playerType, Field field) {

        String fieldType = field.getClass().getSimpleName();
        boolean canPlay = false;

        if (playerType.equals("Women") && fieldType.equals("ArtificialTurf")) {
            canPlay = true;
        } else if (playerType.equals("Men") && fieldType.equals("NaturalGrass")) {
            canPlay = true;
        }

        return canPlay;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.get(fieldName);
        field.drag();
        return String.format(PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.get(fieldName);
        int totalStrength = field.getPlayers().stream()
                .mapToInt(Player::getStrength)
                .sum();
        return String.format(STRENGTH_FIELD, fieldName, totalStrength);
    }

    @Override
    public String getStatistics() {
        return fields.values().stream().map(Field::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}
