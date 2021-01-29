package com.kozirase.app;

public class Mood {
    private String excite;
    private String pleasant;
    private String calm;
    private String nervous;
    private String boring;
    private String unpleasant;
    private String surprise;
    private String sleepy;
    private String myakuari;



    public Mood(String excite, String pleasant, String calm, String nervous, String boring, String unpleasant, String surprise, String sleepy, String myakuari) {
        this.excite = excite;
        this.pleasant = pleasant;
        this.calm = calm;
        this.nervous = nervous;
        this.boring = boring;
        this.unpleasant = unpleasant;
        this.surprise = surprise;
        this.sleepy = sleepy;
        this.myakuari = myakuari;

    }




    public String getExcite() {
        return excite;
    }

    public String getPleasant() {
        return pleasant;
    }

    public String getCalm() {
        return calm;
    }

    public String getNervous() {
        return nervous;
    }

    public String getBoring() {
        return boring;
    }

    public String getUnpleasant() {
        return unpleasant;
    }

    public String getSurprise() {
        return surprise;
    }

    public String getSleepy() {
        return sleepy;
    }

    public String getMyakuari() {
        return myakuari;
    }

}

