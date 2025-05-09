package com.classig.dbgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public void AddClient(String surname, String name, String patr, String birth, String categ)
    {
        Client client = new Client(surname, name, patr, birth, categ);
        listClient.add(client);
    }

    /**
     * Удаление клиента
     * @param i индекс клиента в списке
     */
    public void DelClient(int i)
    {
        listClient.remove(i);
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
    public void EditClient(String surname, String name, String patr, String birth, String categ, int i)
    {
        listClient.get(i).setCategory(categ);
        listClient.get(i).setBirthDate(birth);
        listClient.get(i).setName(name);
        listClient.get(i).setSurName(surname);
        listClient.get(i).setPatrynomic(patr);
    }

    /**
     * Загрузка базы данных из файла
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
                    listClient.add(new Client(part[0], part[1], part[2], part[3], part[4]));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Сохранение базы данных в файл
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
