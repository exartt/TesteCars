package com.LMS.TesteCars.Enum;

public enum RequestType {
    List("Listagem"),
    Create("Criação");

    public final String label;

    private RequestType(String label) {
        this.label = label;
    }
}
