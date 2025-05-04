package com.classig.dbgui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;

public class DBController {
    @FXML
    MenuItem About;

    @FXML
    TableView DBTable;

    @FXML
    TableColumn Name;

    @FXML
    TableColumn SurName;

    @FXML
    TableColumn Patrynomic;

    @FXML
    TableColumn BirthDate;

    @FXML
    TableColumn Category;

    @FXML
    TextField SurNameTF;

    @FXML
    TextField NameTF;

    @FXML
    TextField PatrTF;

    @FXML
    TextField BirthDateTF;

    @FXML
    TextField CategoryTF;


    private final DataBase db = new DataBase();

    private TableView.TableViewSelectionModel<Client> selectionModel;

    public void initialize()
    {
        SurName.setCellValueFactory(new PropertyValueFactory<>("SurName"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Patrynomic.setCellValueFactory(new PropertyValueFactory<>("Patrynomic"));
        BirthDate.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));

        DBTable.setItems(db.getListClient());

        selectionModel = DBTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observableValue, Client oldClient, Client newClient) {
                if (newClient != null)
                {
                    SurNameTF.setText(newClient.getSurName());
                    NameTF.setText(newClient.getName());
                    PatrTF.setText(newClient.getPatrynomic());
                    BirthDateTF.setText(newClient.getBirthDate());
                    CategoryTF.setText(newClient.getCategory());
                }
            }
        }
        );

    }

    public void onClickAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText(null);
        alert.setContentText("База данных с информацией о клинтах больницы: ФИО, Дата рождения, Категория.\nАвтор: Игонин В.Ю.");
        alert.showAndWait();
    }

    public void onClickAdd()
    {
        final String noerr = "-fx-background-color: white;";
        final String err = "-fx-background-color: red;";
        SurNameTF.setStyle(noerr);
        NameTF.setStyle(noerr);
        BirthDateTF.setStyle(noerr);
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getText().isEmpty())
        {
            db.AddClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getText(), CategoryTF.getText());
        }
        else
        {
            if (SurNameTF.getText().isEmpty())
            {
                SurNameTF.setStyle(err);
            }
            if (NameTF.getText().isEmpty())
            {
                NameTF.setStyle(err);
            }
            if (BirthDateTF.getText().isEmpty())
            {
                BirthDateTF.setStyle(err);
            }
        }
    }

    public void onClickEdit()
    {
        final String noerr = "-fx-background-color: white;";
        final String err = "-fx-background-color: red;";
        SurNameTF.setStyle(noerr);
        NameTF.setStyle(noerr);
        BirthDateTF.setStyle(noerr);
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getText().isEmpty())
        {
            db.EditClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getText(), CategoryTF.getText(), selectionModel.getFocusedIndex());
            DBTable.refresh();
        }
        else
        {
            if (SurNameTF.getText().isEmpty())
            {
                SurNameTF.setStyle(err);
            }
            if (NameTF.getText().isEmpty())
            {
                NameTF.setStyle(err);
            }
            if (BirthDateTF.getText().isEmpty())
            {
                BirthDateTF.setStyle(err);
            }
        }
    }

    public void onClickSave()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить базу данных");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showSaveDialog(DBTable.getScene().getWindow());

        if (file != null)
        {
            try {
                db.Save(file.getAbsolutePath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onClickLoad()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить базу данных");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showOpenDialog(DBTable.getScene().getWindow());

        if (file != null)
        {
            try {
                DBTable.getItems().clear();
                db.Load(file.getAbsolutePath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onClickClose()
    {
        onClickSave();
        DBTable.getScene().getWindow().hide();
    }

    public void onClickDel()
    {
        db.DelClient(selectionModel.getFocusedIndex());
    }

}