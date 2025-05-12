package com.classig.dbgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Класс база данных
 * Автор: Игонин В.Ю
 */
public class DataBase {
    /**
     * Наблюдаемый список с клиентами больницы
     */
    private ObservableList<Client> listClient = FXCollections.observableArrayList();

    /**
     * Добавление клиента
     * @param surname фамилия
     * @param name имя
     * @param patr отчество
     * @param birth дата рождения
     * @param categ категория
     */
    public void AddClient(String surname, String name, String patr, LocalDate birth, String categ)
    {
        Client client = new Client(surname, name, patr, birth, categ); // создаем клиента
        listClient.add(client); // добавляем в список
    }

    /**
     * Поиск клиента
     * @param surname фамилия
     * @param name имя
     * @param patr отчество
     * @param birth дата рождения
     * @param categ категория
     * @return индекс клиента в базе данных(списке)
     */
    public int FindClient(String surname, String name, String patr, LocalDate birth, String categ)
    {
        if (patr.isEmpty()) // проверяем введено ли отчество
        {
            patr = "-"; // если нет то ставится -
        }
        if (categ.isEmpty()) // проверяем введена ли категория
        {
            categ = "-"; // если нет то ставится -
        }
        for (int i = 0; i < listClient.size(); i++) // проходим по списку (базе данных)
        {
            if (
                    listClient.get(i).getName().equalsIgnoreCase(name) && listClient.get(i).getSurName().equalsIgnoreCase(surname) && // сверяем данные о клинте
                    listClient.get(i).getPatrynomic().equalsIgnoreCase(patr) && listClient.get(i).getBirthDate().equals(birth) &&
                    listClient.get(i).getCategory().equalsIgnoreCase(categ)
               )
            {
                return i; // возращаем индекс
            }
        }
        throw new OutOfMemoryError("Нет такого человека"); // если нет человека
    }

    /**
     * Удаление клиента
     * @param i индекс клиента в списке
     */
    public void DelClient(int i)
    {
        if (i < listClient.size()) // если индекс в пределах базы данных (списка)
        {
            listClient.remove(i); // удаляем клиента из списка
        }
        else // индекс за пределами массива
        {
            throw new OutOfMemoryError("Клиента с таким индексом нет"); // кидаем ошибку
        }
    }

    /**
     * Редактирование информации о клиенте
     * @param surname фамилия
     * @param name имя
     * @param patr отчество
     * @param birth дата рождения
     * @param categ категория
     * @param i индекс клиента в списке
     */
    public void EditClient(String surname, String name, String patr, LocalDate birth, String categ, int i)
    {
        if (i < listClient.size()) { // проходим по списку(базе дынных)
            listClient.get(i).setCategory(categ); // задаем новую информацию о клиенте
            listClient.get(i).setBirthDate(birth);
            listClient.get(i).setName(name);
            listClient.get(i).setSurName(surname);
            listClient.get(i).setPatrynomic(patr);
        }
        else
        {
            throw new OutOfMemoryError("Нет такого индекса"); // если индекс за пределами массива
        }
    }

    /**
     * Загрузка базы данных из файла.
     * Работает с текстовыми файлами.
     * @param file имя файла
     */
    public void Load(String file)
    {
        if (Files.exists(Paths.get(file)))
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String[] part = line.split(" ; ");
                    listClient.add(new Client(part[0], part[1], part[2], LocalDate.parse(part[3]), part[4]));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Сохранение базы данных в файл.
     * Работает с текстовыми (txt).
     * Каждая строка файла - отдельный клиент: фамилия; имя; отчество; дата рождения(YYYY-MM-DD); категория
     * Пример строки файла: f ; g ; - ; 2025-05-23 ; -
     * @param file имя файла
     */
    public void Save(String file)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            for (Client client : listClient)
            {
                writer.write(client.toString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение списка с клиентами
     * @return список с клиентами больницы
     */
    public ObservableList<Client> getListClient()
    {
        return listClient;
    }
}
