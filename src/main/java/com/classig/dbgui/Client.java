package com.classig.dbgui;

public class Client {

    private String Name;

    private String SurName;

    private String Patrynomic;

    private String BirthDate;

    private String Category;

    Client(String surname, String name, String patrynomic, String birthDate, String category)
    {
        setSurName(surname);
        setName(name);
        setPatrynomic(patrynomic);
        setBirthDate(birthDate);
        setCategory(category);
    }

    public void setName(String name)
    {
        if (!name.isEmpty())
        {
            this.Name = name;
        }
    }

    public void setSurName(String surname)
    {
        if (!surname.isEmpty())
        {
            this.SurName = surname;
        }
    }

    public void setPatrynomic(String patrynomic)
    {
        if (!patrynomic.isEmpty()) {
            this.Patrynomic = patrynomic;
        }
        else
        {
            this.Patrynomic = "-";
        }
    }

    public void setBirthDate(String birthdate)
    {
        if (!birthdate.isEmpty())
        {
            this.BirthDate = birthdate;
        }
    }

    public void setCategory(String category) {
        if (!category.isEmpty())
        {
        this.Category = category;
        }
        else
        {
            this.Category = "-";
        }
    }

    public final String getName()
    {
        return Name;
    }

    public final String getSurName()
    {
        return SurName;
    }

    public final String getPatrynomic()
    {
        return Patrynomic;
    }

    public final String getBirthDate()
    {
        return BirthDate;
    }

    public final String getCategory()
    {
        return Category;
    }

    @Override
    public final String toString()
    {
        return SurName+" ; "+Name+" ; "+Patrynomic+" ; "+BirthDate+" ; "+Category;
    }

}
