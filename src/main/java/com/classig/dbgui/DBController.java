package com.classig.dbgui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class DBController {
    @FXML
    MenuItem About;

    public void onClickAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText(null);
        alert.setContentText("База данных с информацией о клинтах больницы: ФИО, Дата рождения, Категория.\nАвтор: Игонин В.Ю.");
        alert.showAndWait();
    }

}