package magicGame.models.magics;

public class RedMagic extends MagicImpl {
    private static final int DEFAULT_BULLET = 1;

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        return doFire(DEFAULT_BULLET);
    }
}
