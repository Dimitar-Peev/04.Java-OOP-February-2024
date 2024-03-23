package bank.common;

public class StringUtils {
    // клас с множество Custom методи
    public StringUtils() {
    }

    public static boolean nullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
}
