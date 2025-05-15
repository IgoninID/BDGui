package com.classig.dbgui;

import javafx.application.Application;

/**
 * Новый главный класс для приложения
 * Нужен для создания JAR-файла
 */
public class Launcher {
    public static void main(String[] args)
    {
        Application.launch(DBApplication.class, args);
    }
}
