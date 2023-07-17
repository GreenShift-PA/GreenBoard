package com.greenshift.greenboard.models;

import java.time.LocalDateTime;

public class SymVer {

    private String prefix = "";
    private final Integer major;
    private final Integer minor;
    private final Integer patch;

    private LocalDateTime releaseDate = null;

    public SymVer(Integer major, Integer minor, Integer patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public SymVer(String prefix, Integer major, Integer minor, Integer patch, LocalDateTime releaseDate) {
        this.prefix = prefix;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseDate = releaseDate;
    }

    public Integer getMajor() {
        return major;
    }

    public Integer getMinor() {
        return minor;
    }

    public Integer getPatch() {
        return patch;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return String.format("v%s%d.%d.%d", prefix, major, minor, patch);
    }
}
