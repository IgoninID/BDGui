package com.classig.dbgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

//todo измени цвет текста

public class DBApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DBApplication.class.getResource("BDview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.getIcons().add(new Image("file:src/main/resources/com/classig/dbgui/icon.png"));
        stage.setTitle("Клиенты больницы");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}