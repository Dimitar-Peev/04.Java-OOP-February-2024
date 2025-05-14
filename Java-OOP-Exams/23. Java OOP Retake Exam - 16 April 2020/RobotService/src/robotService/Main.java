package robotService;

import robotService.core.EngineImpl;
import robotService.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
