package CounterStriker.models.guns;

public class Pistol extends GunImpl {

    private static final int BULLETS_PER_SHOT = 1;

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() < BULLETS_PER_SHOT) {
            return 0;
        }

        int bulletsCount = super.getBulletsCount() - BULLETS_PER_SHOT;
        super.setBulletsCount(Math.max(bulletsCount, 0));
        return BULLETS_PER_SHOT;
    }

}
