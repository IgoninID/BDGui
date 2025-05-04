package com.classig.dbgui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataBase {
    private ObservableList<Client> listClient = FXCollections.observableArrayList();

    public void AddClient(String surname, String name, String patr, String birth, String categ)
    {
        Client client = new Client(surname, name, patr, birth, categ);
        listClient.add(client);
    }

    public void DelClient(int i)
    {
        listClient.remove(i);
    }

    public void EditClient(String surname, String name, String patr, String birth, String categ, int i)
    {
        listClient.get(i).setCategory(categ);
        listClient.get(i).setBirthDate(birth);
        listClient.get(i).setName(name);
        listClient.get(i).setSurName(surname);
        listClient.get(i).setPatrynomic(patr);
    }

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

    public ObservableList<Client> getListClient()
    {
        return listClient;
    }
}
