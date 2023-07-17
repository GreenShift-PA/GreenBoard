package com.greenshift.greenboard.exporters;

import com.greenshift.greenboard.interfaces.IVisitor;

public abstract class BaseExporter implements IVisitor {
    protected String outputFileName = null;

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public abstract String getOutput();

    public abstract void clear();

    public void export() {
        if(outputFileName == null) {
            System.out.println("Output file name is null");
            return;
        }

        String output = getOutput();

        if(output == null) {
            System.out.println("Output is null");
            return;
        }

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(outputFileName);
            fileWriter.write(output);
            fileWriter.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
