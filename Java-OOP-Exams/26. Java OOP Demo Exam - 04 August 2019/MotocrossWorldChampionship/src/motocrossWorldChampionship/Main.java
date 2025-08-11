package motocrossWorldChampionship;

import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
