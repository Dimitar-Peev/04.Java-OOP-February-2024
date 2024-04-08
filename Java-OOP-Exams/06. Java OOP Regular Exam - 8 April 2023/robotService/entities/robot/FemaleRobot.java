package robotService.entities.robot;

public class FemaleRobot extends BaseRobot{
    private static final int KILOGRAMS = 7;
    private static final int INCREASE = 1;
    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }
//    @Override
//    public void eating(){
//        super.setKilograms(super.getKilograms()+INCREASE);
//    }

    @Override
    protected int getIncreaseKilograms() {
        return INCREASE;
    }
}
