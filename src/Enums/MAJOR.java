package Enums;

import Application.LangManager;

public enum MAJOR {
    IS("major.is", SCHOOLS.SITE, PROGRAMS.BACHELOR),
    VTiPO("major.vtipo", SCHOOLS.SITE, PROGRAMS.BACHELOR),
    AiU("major.aiu", SCHOOLS.SITE, PROGRAMS.BACHELOR),
    RiM("major.rim", SCHOOLS.SITE, PROGRAMS.BACHELOR),
    IT_MANAGEMENT("major.it_management", SCHOOLS.SITE, PROGRAMS.BACHELOR),

    CYBER_SECURITY_MASTER("major.cyber_security", SCHOOLS.SITE, PROGRAMS.MASTER),

    FINANCE("major.bs_finance", SCHOOLS.BS, PROGRAMS.BACHELOR),
    MARKETING("major.bs_marketing", SCHOOLS.BS, PROGRAMS.BACHELOR);

    private final String key;
    private final SCHOOLS school;
    private final PROGRAMS degreeLevel;

    MAJOR(String key, SCHOOLS school, PROGRAMS degreeLevel) {
        this.key = key;
        this.school = school;
        this.degreeLevel = degreeLevel;
    }

    public String getTranslatedName() {
        return LangManager.get(this.key);
    }

    public SCHOOLS getSchool() {
        return school;
    }

    public PROGRAMS getDegreeLevel() {
        return degreeLevel;
    }
}
