package utils;

public class Utils {

    public static String removeSpecialCharacters(String str) {

        return str.replaceAll("[\\^$.|?*+():/%#@!`\"';+-,~T8]", "");
    }
}
