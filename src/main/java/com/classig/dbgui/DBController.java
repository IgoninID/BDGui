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
     * Строка состояния
     */
    @FXML
    Label Status;

    /**
     * Кнопка меню сохранить
     */
    @FXML
    MenuItem Save;

    /**
     * Кнопка меню загрузить
     */
    @FXML
    MenuItem Load;

    /**
     * Кнопка меню закрыть
     */
    @FXML
    MenuItem Close;

    /**
     * Кнопка меню добавить
     */
    @FXML
    MenuItem Add;

    /**
     * Кнопка меню удалить
     */
    @FXML
    MenuItem Delete;

    /**
     * Кнопка меню редактировать
     */
    @FXML
    MenuItem Edit;

    /**
     * Кнопка меню найти
     */
    @FXML
    MenuItem Find;

    /**
     * Кнопка сохранить
     */
    @FXML
    Button SaveB;

    /**
     * Кнопка загрузить
     */
    @FXML
    Button LoadB;

    /**
     * Кнопка добавить
     */
    @FXML
    Button AddB;

    /**
     * Кнопка удалить
     */
    @FXML
    Button DeleteB;

    /**
     * Кнопка редактировать
     */
    @FXML
    Button EditB;

    /**
     * Кнопка найти
     */
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
     * selectionmodel для получения доступа к объекту показанному в таблице
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

        // инициализация selectionmodel
        selectionModel = DBTable.getSelectionModel();

        // Добавление слушателя для обновления полей ввода при выборе объекта в таблице
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
     * Нажатие на кнопку меню о программе
     */
    public void onClickAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText(null);
        alert.setContentText("База данных с информацией о клиентах больницы: ФИО, Дата рождения, Категория\n\n" +
                "Интерфейс: меню с кнопками связанные с горячими клавишами; поля и кнопки при наведении на которые появляется всплывающая подсказка; таблица с клиентами больницы; строка состояния над полем с кнопками\n\n" +
                "Обязательные поля: Фамилия, Имя, Дата рождения;\n\n" +
                "Необязательные поля: Отчество, Категория - можно не заполнять если нет необходимости, автоматически поставится прочерк(-)\n\n" +
                "Работа c базой данных:\n" +
                "Добавление клиента: Вводим данные клиента и нажимаем на добавить в меню (действия) или кнопку или Ctrl+A\n\n" +
                "Удаление клиента: Выбираем в таблице клиента и нажимаем на удалить в меню (действия) или кнопку или Ctrl+D\n\n" +
                "Редактирование клиента: Выбираем в таблице клиента и меняем данные в полях, после нажимаем на редактировать в меню (действия) или кнопку или Ctrl+E\n\n" +
                "Поиск клиента: Вводим в поля данные клиента и нажимаем на поиск в меню (действия) или кнопку или Ctrl+F\n\n" +
                "Сохранение базы данных: Нажимаем на кнопку в меню (файл) сохранить или Ctrl+S, после выбираем в появившемся окне текстовый файл или создаем его\n\n" +
                "Загрузка базы данных: Нажимаем на кнопку в меню (файл) загрузить или Ctrl+L, после выбираем в появившемся окне текстовый файл с базой данных\n\n" +
                "Закрытие базы данных с сохранением: Нажимаем на кнопку в меню (файл) закрыть или Ctrl+C, после выбираем в появившемся окне текстовый файл или создаем его, после сохранения программа завершает работу\n\n" +
                "Получение информации о программе (данная справка): Нажимаем на кнопку в меню (помощь) о программе или Alt+A\n\n" +
                "Автор: Игонин В.Ю.");
        alert.showAndWait();
    }

    /**
     * Нажатие на кнопку/кнопку меню добавить
     */
    public void onClickAdd()
    {
        final String noerr = "-fx-background-color: white;"; // цвет поля по умолчанию
        final String err = "-fx-background-color: pink;"; // цвет поля при ошибке
        final String noerrstatus = "-fx-text-fill : green"; // цвет текста строки состояния без ошибки
        final String errstatus = "-fx-text-fill : red"; // цвет текста строки состояния при возникновении ошибки

        SurNameTF.setStyle(noerr); // задаем цвет полей по умолчанию
        NameTF.setStyle(noerr);
        BirthDateTF.getEditor().setStyle(noerr);

        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getEditor().getText().isEmpty()) // проверяем введены ли обязательные поля
        {
            db.AddClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getValue(), CategoryTF.getText()); // добавляем клиента в базу данных если введены
            Status.setText("Клиент добавлен"); // обновляем строку состояния
            Status.setStyle(noerrstatus); // задаем цвет текста строки состояния без ошибок
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) // если не введена фамилия
            {
                SurNameTF.setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустая фамилия"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
            if (NameTF.getText().isEmpty()) // если не введено имя
            {
                NameTF.setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустое имя"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
            if (BirthDateTF.getEditor().getText().isEmpty()) // если не введена дата рождения
            {
                BirthDateTF.getEditor().setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустая дата рождения"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
        }
    }

    /**
     * Нажатие на кнопку/кнопку меню редактировать
     */
    public void onClickEdit()
    {
        final String noerr = "-fx-background-color: white;"; // цвет поля по умолчанию
        final String err = "-fx-background-color: pink;"; // цвет поля при ошибке
        final String noerrstatus = "-fx-text-fill : green"; // цвет текста строки состояния без ошибки
        final String errstatus = "-fx-text-fill : red"; // цвет текста строки состояния при возникновении ошибки

        SurNameTF.setStyle(noerr); // задаем цвет полей по умолчанию
        NameTF.setStyle(noerr);
        BirthDateTF.getEditor().setStyle(noerr);
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getEditor().getText().isEmpty()) // проверяем введены ли обязательные поля
        {
            try
            {
                if (selectionModel.getFocusedIndex() != -1) // если не пустая база данных
                {
                    db.EditClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getValue(), CategoryTF.getText(), selectionModel.getFocusedIndex());  // редактируем клиента в базе данных если введены
                    DBTable.refresh(); // обновляем таблицу
                    Status.setText("Информация о клиенте " + (selectionModel.getFocusedIndex()+1) + " отредактирована"); // обновляем строку состояния
                    Status.setStyle(noerrstatus); // задаем цвет текста строки состояния без ошибок
                }
                else // если база данных пуста
                {
                    Status.setText("Пустая база данных"); // обновляем строку состояния
                    Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
                }
            }
            catch (OutOfMemoryError e) // если индекс за пределами списка
            {
                Status.setText(e.getMessage()); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) // если не введена фамилия
            {
                SurNameTF.setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустая фамилия"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
            if (NameTF.getText().isEmpty()) // если не введено имя
            {
                NameTF.setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустое имя"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
            if (BirthDateTF.getEditor().getText().isEmpty()) // если не введена дата рождения
            {
                BirthDateTF.getEditor().setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустая дата рождения"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
        }
    }

    /**
     * Нажатие на кнопку/кнопку меню найти
     */
    public void onClickFind()
    {
        final String noerr = "-fx-background-color: white;"; // цвет поля по умолчанию
        final String err = "-fx-background-color: pink;"; // цвет поля при ошибке
        final String noerrstatus = "-fx-text-fill : green"; // цвет текста строки состояния без ошибки
        final String errstatus = "-fx-text-fill : red"; // цвет текста строки состояния при возникновении ошибки

        SurNameTF.setStyle(noerr); // задаем цвет полей по умолчанию
        NameTF.setStyle(noerr);
        BirthDateTF.getEditor().setStyle(noerr);
        if (!SurNameTF.getText().isEmpty() && !NameTF.getText().isEmpty() && !BirthDateTF.getEditor().getText().isEmpty()) // проверяем введены ли обязательные поля
        {

            try
            {
                int i = db.FindClient(SurNameTF.getText(), NameTF.getText(), PatrTF.getText(), BirthDateTF.getValue(), CategoryTF.getText()); // ищем клиента в базе данных если введены
                selectionModel.select(i); // указываем клиента в таблице
                Status.setText("Клиент найден "+(i+1)); // обновляем строку состояния
                Status.setStyle(noerrstatus); // задаем цвет текста строки состояния без ошибок
            }
            catch (OutOfMemoryError e) // если не найден или индекс за пределами массива
            {
                Status.setText(e.getMessage()); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
        }
        else
        {
            if (SurNameTF.getText().isEmpty()) // если не введена фамилия
            {
                SurNameTF.setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустая фамилия"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
            if (NameTF.getText().isEmpty()) // если не введено имя
            {
                NameTF.setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустое имя"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
            if (BirthDateTF.getEditor().getText().isEmpty()) // если не введена дата рождения
            {
                BirthDateTF.getEditor().setStyle(err); // задаем цвет поля ошибка
                Status.setText("Пустая дата рождения"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
        }
    }

    /**
     * Нажатие на кнопку/кнопку меню сохранить
     */
    public void onClickSave()
    {
        final String noerrstatus = "-fx-text-fill : green"; // цвет текста строки состояния без ошибки
        final String errstatus = "-fx-text-fill : red"; // цвет текста строки состояния при возникновении ошибки

        FileChooser fileChooser = new FileChooser(); // диалоговое окно с выбором файла
        fileChooser.setTitle("Сохранить базу данных"); // задаем заголовок диалогового окна

        fileChooser.getExtensionFilters().addAll( // добавляем фильтры файлов
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showSaveDialog(DBTable.getScene().getWindow()); // показываем диалоговое окно для сохранения

        if (file != null) // если файл выбран
        {
            try {
                db.Save(file.getAbsolutePath()); // сохраняем базу данных в файл
                Status.setText("База данных сохранена по адресу "+file.getAbsolutePath()); // обновляем строку состояния
                Status.setStyle(noerrstatus); // задаем цвет текста строки состояния без ошибок
            }
            catch (Exception e) // произошла ошибка
            {
                Status.setText("База данных не сохранена"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
                e.printStackTrace();
            }
        }
    }

    /**
     * Нажатие на кнопку/кнопку меню загрузить
     */
    public void onClickLoad()
    {
        final String noerrstatus = "-fx-text-fill : green"; // цвет текста строки состояния без ошибки
        final String errstatus = "-fx-text-fill : red"; // цвет текста строки состояния при возникновении ошибки

        FileChooser fileChooser = new FileChooser(); // диалоговое окно с выбором файла
        fileChooser.setTitle("Загрузить базу данных"); // задаем заголовок диалогового окна

        fileChooser.getExtensionFilters().addAll( // добавляем фильтры файлов
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showOpenDialog(DBTable.getScene().getWindow()); // показываем диалоговое окно для загрузкм

        if (file != null) // если файл выбран
        {
            try {
                DBTable.getItems().clear(); // очищаем базу данных в таблице
                db.Load(file.getAbsolutePath()); // загружаем базу данных
                Status.setText("База данных загружена из файла по адресу "+file.getAbsolutePath()); // обновляем строку состояния
                Status.setStyle(noerrstatus); // задаем цвет текста строки состояния без ошибок
            }
            catch (Exception e) // произошла ошибка
            {
                Status.setText("База данных не загружена"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
                e.printStackTrace();
            }
        }
    }

    /**
     * Нажатие на кнопку меню закрыть
     */
    public void onClickClose()
    {
        onClickSave(); // сохраняем базу данных
        DBTable.getScene().getWindow().hide(); // закрываем окно
    }

    /**
     * Нажатие на кнопку/кнопку меню удалить
     */
    public void onClickDel()
    {
        final String noerrstatus = "-fx-text-fill : green"; // цвет текста строки состояния без ошибки
        final String errstatus = "-fx-text-fill : red"; // цвет текста строки состояния при возникновении ошибки

        try {
            if (selectionModel.getFocusedIndex() != -1) // если база данных не пуста
            {
                db.DelClient(selectionModel.getFocusedIndex()); //
                Status.setText("Клиент удален"); // обновляем строку состояния
                Status.setStyle(noerrstatus); // задаем цвет текста строки состояния без ошибок
            }
            else // если пустая база данных
            {
                Status.setText("Пустая база данных"); // обновляем строку состояния
                Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
            }
        }
        catch (OutOfMemoryError e) // произошла ошибка
        {
            Status.setText(e.getMessage()); // обновляем строку состояния
            Status.setStyle(errstatus); // задаем цвет текста строки состояния ошибка
        }
    }

}