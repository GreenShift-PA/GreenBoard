package com.greenshift.greenboard.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greenshift.greenboard.exporters.JsonExporter;
import com.greenshift.greenboard.exporters.XmlExporter;
import com.greenshift.greenboard.interfaces.IDumper;
import com.greenshift.greenboard.models.entities.BaseEntity;
import com.greenshift.greenboard.services.*;
import com.greenshift.greenboard.singletons.ApplicationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class StorageUtils {

    public static String dump(IDumper dumper) {
        if(dumper == null) return "";
        return dumper.dump();
    }

    public static boolean dumpToFile(IDumper dumper, String fileName) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
            fileWriter.write(dump(dumper));
            fileWriter.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveToFile(String content, String fileName) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String data = dump(new JsonExporter());
        saveToFile(data, "data.json");
        data = dump(new XmlExporter());
        saveToFile(data, "data.xml");
    }

}
