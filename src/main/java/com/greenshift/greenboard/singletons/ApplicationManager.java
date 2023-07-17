package com.greenshift.greenboard.singletons;

import com.greenshift.greenboard.models.SymVer;

public class ApplicationManager {
    private  String name;
    private  String author;
    private SymVer symVer;

    private boolean isDebug = false;
    private boolean isVerbose = false;
    private boolean isLatestVersion = false;

    private static ApplicationManager instance = null;

    public ApplicationManager() {
        this.name = "GreenBoard";
        this.author = "GreenShift";
        this.symVer = new SymVer(1, 0, 0);
    }

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public ApplicationManager(String name, String author, SymVer symVer) {
        this.name = name;
        this.author = author;
        this.symVer = symVer;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public SymVer getVersion() {
        return symVer;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public boolean isVerbose() {
        return isVerbose;
    }

    public void setVerbose(boolean verbose) {
        isVerbose = verbose;
    }

    public boolean isLatestVersion() {
        return isLatestVersion;
    }

    public void setLatestVersion(boolean latestVersion) {
        isLatestVersion = latestVersion;
    }



}
