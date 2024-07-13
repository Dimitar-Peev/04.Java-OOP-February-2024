package magicGame.models.magics;

public class BlackMagic extends MagicImpl {
    private static final int DEFAULT_BULLET = 10;

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        return doFire(DEFAULT_BULLET);
    }
}
