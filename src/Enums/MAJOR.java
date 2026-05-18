package Enums;

import Application.LangManager;

public enum MAJOR {
    IS("major.is"),
    VTiPO("major.vtipo"),
    AiU("major.aiu"),
    RiM("major.rim"),
    IT_MANAGEMENT("major.it_management");

    private final String key;

    MAJOR(String key) {
        this.key = key;
    }

    public String getTranslatedName() {
        return LangManager.get(this.key);
    }
}
