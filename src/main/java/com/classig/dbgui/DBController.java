package com.classig.dbgui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Контроллер для базы данных
 * Автор: Игонин В.Ю
 */
public class DBController {
    /**
     * Кнопка меню о программе
     */
    @FXML
    MenuItem About;

    /**
     * Таблица с клиентами больницы
     */
    @FXML
    TableView DBTable;

    /**
     * Колонка таблицы имя
     */
    @FXML
    TableColumn Name;

    /**
     * Колонка таблицы фамилия
     */
    @FXML
    TableColumn SurName;

    /**
     * Колонка таблицы отчество
     */
    @FXML
    TableColumn Patrynomic;

    /**
     * Колонка таблицы дата рождения
     */
    @FXML
    TableColumn BirthDate;

    /**
     * Колонка таблицы категория
     */
    @FXML
    TableColumn Category;

    /**
     * Поле ввода фамилии
     */
    @FXML
    TextField SurNameTF;

    /**
     * Поле ввода имени
     */
    @FXML
    TextField NameTF;

    /**
     * Поле ввода отчества
     */
    @FXML
    TextField PatrTF;

    /**
     * Поле ввода даты рождения
     */
    @FXML
    TextField BirthDateTF;

    /**
     * Поле ввода категории
     */
    @FXML
    TextField CategoryTF;

    /**
     * База данных с клиентами больницы
     */
    private final DataBase db = new DataBase();

    /**
     *
     */
    private TableView.TableViewSelectionModel<Client> selectionModel;

    /**
     * Инициализация
     */
    public void initialize()
    {
        //
        SurName.setCellValueFactory(new PropertyValueFactory<>("SurName"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Patrynomic.setCellValueFactory(new PropertyValueFactory<>("Patrynomic"));
        BirthDate.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));

        //
        DBTable.setItems(db.getListClient());

        //
        selectionModel = DBTable.getSelectionModel();

        //
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

    /**
     * Нажатие на кнопку о программе
     */
    public void onClickAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText(null);
        alert.setContentText("База данных с информацией о клиентах больницы: ФИО, Дата рождения, Категория.\nАвтор: Игонин В.Ю.");
        alert.showAndWait();
    }

    /**
     * Нажатие на кнопку добавить
     */
    public void onClickAdd()
    {
        final String noerr = "-fx-background-color: white;"; //
        final String err = "-fx-background-color: pink;"; //
        SurNameTF.setStyle(noerr); //
        NameTF.setStyle(noerr);
        BirthDateTF.setStyle(noerr);
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getText().isEmpty()) //
        {
            db.AddClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getText(), CategoryTF.getText()); //
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) //
            {
                SurNameTF.setStyle(err); //
            }
            if (NameTF.getText().isEmpty()) //
            {
                NameTF.setStyle(err); //
            }
            if (BirthDateTF.getText().isEmpty()) //
            {
                BirthDateTF.setStyle(err); //
            }
        }
    }

    /**
     * Нажатие на кнопку редактировать
     */
    public void onClickEdit()
    {
        final String noerr = "-fx-background-color: white;"; //
        final String err = "-fx-background-color: pink;"; //
        SurNameTF.setStyle(noerr); //
        NameTF.setStyle(noerr); //
        BirthDateTF.setStyle(noerr); //
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getText().isEmpty()) //
        {
            db.EditClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getText(), CategoryTF.getText(), selectionModel.getFocusedIndex()); //
            DBTable.refresh(); //
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) //
            {
                SurNameTF.setStyle(err); //
            }
            if (NameTF.getText().isEmpty()) //
            {
                NameTF.setStyle(err); //
            }
            if (BirthDateTF.getText().isEmpty()) //
            {
                BirthDateTF.setStyle(err); //
            }
        }
    }

    /**
     * Нажатие на кнопку сохранить
     */
    public void onClickSave()
    {
        FileChooser fileChooser = new FileChooser(); //
        fileChooser.setTitle("Сохранить базу данных"); //

        fileChooser.getExtensionFilters().addAll( //
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showSaveDialog(DBTable.getScene().getWindow()); //

        if (file != null) //
        {
            try {
                db.Save(file.getAbsolutePath()); //
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Нажатие на кнопку загрузить
     */
    public void onClickLoad()
    {
        FileChooser fileChooser = new FileChooser(); //
        fileChooser.setTitle("Загрузить базу данных"); //

        fileChooser.getExtensionFilters().addAll( //
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showOpenDialog(DBTable.getScene().getWindow()); //

        if (file != null) //
        {
            try {
                DBTable.getItems().clear(); //
                db.Load(file.getAbsolutePath()); //
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Нажатие на кнопку меню закрыть
     */
    public void onClickClose()
    {
        onClickSave(); //
        DBTable.getScene().getWindow().hide(); //
    }

    /**
     * Нажатие на кнопку удалить
     */
    public void onClickDel()
    {
        db.DelClient(selectionModel.getFocusedIndex()); //
    }

}