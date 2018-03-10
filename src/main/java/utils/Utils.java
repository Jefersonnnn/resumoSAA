package utils;

import java.io.File;
import java.text.Normalizer;

public class Utils {

    public static String removeSpecialCharacters(String str) {
        return str.replaceAll("[\\^$.|?*+():]", "");
    }
}
