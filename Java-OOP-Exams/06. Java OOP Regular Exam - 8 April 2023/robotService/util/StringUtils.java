package robotService.util;

public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isBlank();
    }

    public static String toUnderscoreString(String input) {
        // MainService -> MAIN_SERVICE
        // mainService -> MAIN_SERVICE
        // TODO check "-"
        StringBuilder sb = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];
            if (Character.isUpperCase(current) && i > 0) {
                sb.append('_');
                sb.append(current);
            } else {
                sb.append(Character.toUpperCase(current));
            }
        }
        return sb.toString();
    }
}
