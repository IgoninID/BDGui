package com.classig.dbgui;

/**
 * Класс клиент больницы
 * Автор: Игонин В.Ю
 */
public class Client {

    /**
     * Поле имя
     */
    private String Name;

    /**
     * Поле фамилия
     */
    private String SurName;

    /**
     * Поле отчество
     */
    private String Patrynomic;

    /**
     * Поле дата рождения
     */
    private String BirthDate;

    /**
     * Поле категория
     */
    private String Category;

    /**
     * Конструктор с параметрами
     * @param surname фамилия
     * @param name имя
     * @param patrynomic отчество
     * @param birthDate дата рождения
     * @param category категория
     */
    Client(String surname, String name, String patrynomic, String birthDate, String category)
    {
        setSurName(surname);
        setName(name);
        setPatrynomic(patrynomic);
        setBirthDate(birthDate);
        setCategory(category);
    }

    /**
     * Вставка имени
     * Правило ввода - не может быть пустым
     * @param name имя
     */
    public void setName(String name)
    {
        if (!name.isEmpty())
        {
            this.Name = name;
        }
    }

    /**
     * Вставка фамилии
     * Правило ввода - не может быть пустым
     * @param surname фамилия
     */
    public void setSurName(String surname)
    {
        if (!surname.isEmpty())
        {
            this.SurName = surname;
        }
    }

    /**
     * Вставка отчества
     * Если пустое то будет -
     * @param patrynomic отчество
     */
    public void setPatrynomic(String patrynomic)
    {
        if (!patrynomic.isEmpty()) {
            this.Patrynomic = patrynomic;
        }
        else
        {
            this.Patrynomic = "-"; //
        }
    }

    /**
     * Вставка даты рождения
     * Правило ввода - не может быть пустым
     * @param birthdate дата рождения
     */
    public void setBirthDate(String birthdate)
    {
        if (!birthdate.isEmpty())
        {
            this.BirthDate = birthdate;
        }
    }

    /**
     * Вставка категории
     * Если пустое то будет -
     * @param category
     */
    public void setCategory(String category) {
        if (!category.isEmpty())
        {
        this.Category = category;
        }
        else
        {
            this.Category = "-"; //
        }
    }

    /**
     * Получение имени
     * @return имя клиента
     */
    public final String getName()
    {
        return Name;
    }

    /**
     * Получение фамилии
     * @return фамилия клиента
     */
    public final String getSurName()
    {
        return SurName;
    }

    /**
     * Получение отчества
     * @return отчество клиента
     */
    public final String getPatrynomic()
    {
        return Patrynomic;
    }

    /**
     * Получение даты рождения
     * @return дата рождения клиента
     */
    public final String getBirthDate()
    {
        return BirthDate;
    }

    /**
     * Получение категории
     * @return категория клиента
     */
    public final String getCategory()
    {
        return Category;
    }

    /**
     * Получение полной информации о клиенте (фамилия ; имя ; отчество ; дата рождения ; категория)
     * @return информация клиента
     */
    @Override
    public final String toString()
    {
        return SurName+" ; "+Name+" ; "+Patrynomic+" ; "+BirthDate+" ; "+Category;
    }

}
