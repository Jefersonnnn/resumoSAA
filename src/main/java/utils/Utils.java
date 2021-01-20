package utils;

public class Utils {

    public static String removeSpecialCharacters(String str) {
        str = str.replace("T8", "");
        return str.replaceAll("[\\^$.|?*+():/%#@!`\"';+-,~]", "");
    }
}
