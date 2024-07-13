package magicGame.models.magicians;

import magicGame.models.magics.Magic;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician {
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic; // Dependancy inversion

    protected MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
        this.setAlive();
        this.setMagic(magic);
    }

    private void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    private void setProtection(int protection) {
        if (protection < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    private void setAlive() {
        this.isAlive = this.health > 0;
    }

    private void setMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;
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
    public int getProtection() {
        return this.protection;
    }

    @Override
    public Magic getMagic() {
        return this.magic;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getUsername() + System.lineSeparator() +
                "Health: " + getHealth() + System.lineSeparator() +
                "Protection: " + getProtection() + System.lineSeparator() +
                "Magic: " + getMagic().getName();
    }

    @Override
    public void takeDamage(int points) {
        // The takeDamage() method decreases the Magician's protection and health.
        // First, you need to reduce the protection. If the protection reaches 0, transfer the damage to health points.
        // If the health points are less than or equal to zero, the magician is dead.
        if (points > this.protection) {
            int damageTaken = points - protection;
            health = Math.max(health - damageTaken, 0);
            protection = 0;
            setAlive();
        } else {
            this.protection -= points;
        }

    }
}
