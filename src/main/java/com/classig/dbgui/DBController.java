package com.classig.dbgui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

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
     *
     */
    @FXML
    Label Status;

    @FXML
    MenuItem Save;

    @FXML
    MenuItem Load;

    @FXML
    MenuItem Close;

    @FXML
    MenuItem Add;

    @FXML
    MenuItem Delete;

    @FXML
    MenuItem Edit;

    @FXML
    MenuItem Find;

    @FXML
    Button SaveB;

    @FXML
    Button LoadB;

    @FXML
    Button AddB;

    @FXML
    Button DeleteB;

    @FXML
    Button EditB;

    @FXML
    Button FindB;

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
    DatePicker BirthDateTF;

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
        // Связывание колонок и методов Client
        SurName.setCellValueFactory(new PropertyValueFactory<>("SurName"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Patrynomic.setCellValueFactory(new PropertyValueFactory<>("Patrynomic"));
        BirthDate.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));

        // Создание всплывающих окон при наведении на некоторые объекты
        SaveB.setTooltip(new Tooltip("Сохранение базы данных в текстовый файл"));
        LoadB.setTooltip(new Tooltip("Загрузка базы данных из текстового файла"));
        AddB.setTooltip(new Tooltip("Добавление клиента в базу данных"));
        DeleteB.setTooltip(new Tooltip("Удаление клиента из базы данных"));
        EditB.setTooltip(new Tooltip("Редактирование информации о клиенте в базе данных"));
        FindB.setTooltip(new Tooltip("Нахождение клиента в базе данных"));
        SurNameTF.setTooltip(new Tooltip("Поле ввода фамилии, обязательное"));
        NameTF.setTooltip(new Tooltip("Поле ввода имени, обязательное"));
        PatrTF.setTooltip(new Tooltip("Поле ввода отчества, может быть пустое"));
        BirthDateTF.setTooltip(new Tooltip("Поле ввода даты рождения, обязательное"));
        CategoryTF.setTooltip(new Tooltip("Поле ввода категории пациента, может быть пустое"));

        // Добавляем горячие клавиши к кнопкам меню
        Add.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        Delete.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        Find.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        Edit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        Save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        Load.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        Close.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        About.setAccelerator(KeyCombination.keyCombination("Alt+A"));

        // связывание таблицы и базы данных
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
                    BirthDateTF.setValue(newClient.getBirthDate());
                    BirthDateTF.setValue(newClient.getBirthDate());
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
        final String noerrstatus = "-fx-text-fill : green";
        final String errstatus = "-fx-text-fill : red";

        SurNameTF.setStyle(noerr); //
        NameTF.setStyle(noerr);
        BirthDateTF.getEditor().setStyle(noerr);
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getEditor().getText().isEmpty()) //
        {
            db.AddClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getValue(), CategoryTF.getText()); //
            Status.setText("Клиент добавлен");
            Status.setStyle(noerrstatus);
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) //
            {
                SurNameTF.setStyle(err); //
                Status.setText("Пустая фамилия");
                Status.setStyle(errstatus);
            }
            if (NameTF.getText().isEmpty()) //
            {
                NameTF.setStyle(err); //
                Status.setText("Пустое имя");
                Status.setStyle(errstatus);
            }
            if (BirthDateTF.getEditor().getText().isEmpty()) //
            {
                BirthDateTF.getEditor().setStyle(err); //
                Status.setText("Пустая дата рождения");
                Status.setStyle(errstatus);
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
        final String noerrstatus = "-fx-text-fill : green";
        final String errstatus = "-fx-text-fill : red";

        SurNameTF.setStyle(noerr); //
        NameTF.setStyle(noerr); //
        BirthDateTF.getEditor().setStyle(noerr); //
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getEditor().getText().isEmpty()) //
        {
            try
            {
                if (selectionModel.getFocusedIndex() != -1)
                {
                    db.EditClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getValue(), CategoryTF.getText(), selectionModel.getFocusedIndex()); //
                    DBTable.refresh(); //
                    Status.setText("Информация о клиенте " + (selectionModel.getFocusedIndex()+1) + " отредактирована");
                    Status.setStyle(noerrstatus);
                }
                else
                {
                    Status.setText("Пустая база данных");
                    Status.setStyle(errstatus);
                }
            }
            catch (OutOfMemoryError e)
            {
                Status.setText(e.getMessage());
                Status.setStyle(errstatus);
            }
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) //
            {
                SurNameTF.setStyle(err); //
                Status.setText("Пустая фамилия");
                Status.setStyle(errstatus);
            }
            if (NameTF.getText().isEmpty()) //
            {
                NameTF.setStyle(err); //
                Status.setText("Пустое имя");
                Status.setStyle(errstatus);
            }
            if (BirthDateTF.getEditor().getText().isEmpty()) //
            {
                BirthDateTF.getEditor().setStyle(err); //
                Status.setText("Пустая дата рождения");
                Status.setStyle(errstatus);
            }
        }
    }

    public void onClickFind()
    {
        final String noerr = "-fx-background-color: white;"; //
        final String err = "-fx-background-color: pink;"; //
        final String noerrstatus = "-fx-text-fill : green";
        final String errstatus = "-fx-text-fill : red";

        SurNameTF.setStyle(noerr); //
        NameTF.setStyle(noerr); //
        BirthDateTF.getEditor().setStyle(noerr); //
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getEditor().getText().isEmpty()) //
        {

            try
            {
                int i = db.FindClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getValue(), CategoryTF.getText()); //
                selectionModel.select(i);
                Status.setText("Клиент найден "+(i+1));
                Status.setStyle(noerrstatus);
            }
            catch (OutOfMemoryError e)
            {
                Status.setText(e.getMessage());
                Status.setStyle(errstatus);
            }
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) //
            {
                SurNameTF.setStyle(err); //
                Status.setText("Пустая фамилия");
                Status.setStyle(errstatus);
            }
            if (NameTF.getText().isEmpty()) //
            {
                NameTF.setStyle(err); //
                Status.setText("Пустое имя");
                Status.setStyle(errstatus);
            }
            if (BirthDateTF.getEditor().getText().isEmpty()) //
            {
                BirthDateTF.getEditor().setStyle(err); //
                Status.setText("Пустая дата рождения");
                Status.setStyle(errstatus);
            }
        }
    }

    /**
     * Нажатие на кнопку сохранить
     */
    public void onClickSave()
    {
        final String noerrstatus = "-fx-text-fill : green";
        final String errstatus = "-fx-text-fill : red";

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
                Status.setText("База данных сохранена по адресу "+file.getAbsolutePath());
                Status.setStyle(noerrstatus);
            }
            catch (Exception e)
            {
                Status.setText("База данных не сохранена");
                Status.setStyle(errstatus);
                e.printStackTrace();
            }
        }
    }

    /**
     * Нажатие на кнопку загрузить
     */
    public void onClickLoad()
    {
        final String noerrstatus = "-fx-text-fill : green";
        final String errstatus = "-fx-text-fill : red";

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
                Status.setText("База данных загружена из файла по адресу "+file.getAbsolutePath());
                Status.setStyle(noerrstatus);
            }
            catch (Exception e)
            {
                Status.setText("База данных не загружена");
                Status.setStyle(errstatus);
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
        final String noerrstatus = "-fx-text-fill : green";
        final String errstatus = "-fx-text-fill : red";

        try {
            if (selectionModel.getFocusedIndex() != -1)
            {
                db.DelClient(selectionModel.getFocusedIndex()); //
                Status.setText("Клиент удален");
                Status.setStyle(noerrstatus);
            }
            else
            {
                Status.setText("Пустая база данных");
                Status.setStyle(errstatus);
            }
        }
        catch (OutOfMemoryError e)
        {
            Status.setText(e.getMessage());
            Status.setStyle(errstatus);
        }
    }

}