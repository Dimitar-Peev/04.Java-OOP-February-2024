package handball.entities.team;

import static handball.common.ExceptionMessages.*;

public abstract class BaseTeam implements Team {
    private String name;
    private String country;
    private int advantage;

    protected BaseTeam(String name, String country, int advantage) {
        this.setName(name);
        this.setCountry(country);
        this.setAdvantage(advantage);
    }

    private void setCountry(String country) {
        if (country == null || country.isBlank()) {
            throw new NullPointerException(TEAM_COUNTRY_NULL_OR_EMPTY);
        }
        this.country = country;
    }

    protected void setAdvantage(int advantage) {
        if(advantage <= 0){
            throw new IllegalArgumentException(TEAM_ADVANTAGE_BELOW_OR_EQUAL_ZERO);
        }
        this.advantage = advantage;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(TEAM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAdvantage() {
        return this.advantage;
    }
}
