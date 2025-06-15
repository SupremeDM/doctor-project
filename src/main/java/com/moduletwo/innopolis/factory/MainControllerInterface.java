package com.moduletwo.innopolis.factory;

public class MainControllerInterface {
    public Boolean checkFieldFromFrontend(Object field) {
        return field != null && !field.toString().trim().isEmpty();
    }
}
