package Application;

import java.util.Locale;
import java.util.ResourceBundle;

public class LangManager {
    private static ResourceBundle bundle;
    private static Locale currentLocale;

    static {
        setLanguage("en");
    }

    public static void setLanguage(String langCode) {
        currentLocale = new Locale(langCode);
        bundle = ResourceBundle.getBundle("resources.label", currentLocale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}