package com.example.anilturan.sozlukapp.model;

public class Glosbe implements java.io.Serializable {
    private static final long serialVersionUID = -3176160197276085113L;
    private String result;
    private int found;
    private GlosbeExamples[] examples;
    private int pageSize;
    private int page;

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getFound() {
        return this.found;
    }

    public void setFound(int found) {
        this.found = found;
    }

    public GlosbeExamples[] getExamples() {
        return this.examples;
    }

    public void setExamples(GlosbeExamples[] examples) {
        this.examples = examples;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
