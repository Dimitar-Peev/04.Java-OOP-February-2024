package harpoonDiver.core.factory;

import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.Diver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DiverFactory {

    public static Diver createDiver(String type, String name) {
        try {
            Class<?> diverClass = Class.forName("harpoonDiver.models.diver." + type);
            final Constructor<?> constructor = diverClass.getConstructor(String.class);
            return (Diver) constructor.<Diver>newInstance(name);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 InvocationTargetException | IllegalAccessException ex) {
            throw new IllegalArgumentException(ExceptionMessages.DIVER_INVALID_KIND);
        }
    }
}
