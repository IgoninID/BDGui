# Создание установочного файла для JavaFX приложения с использованием Inno Setup

---

## 1. Создание JAR-файла в IntelliJ IDEA

### 1.1. Настройка проекта
1. Создайте новый главный класс Launcher
   ```java
   package com.classig.dbgui;
   import javafx.application.Application;
   /**
    * Новый главный класс для приложения
    * Нужен для создания JAR-файла
    */
   public class Launcher {
       public static void main(String[] args)
       {
           Application.launch(DBApplication.class, args);
       }
   }
2. Укажите главный класс в настройках проекта:
   
   Перейдите в File -> Project Structure -> Project Settings -> Artifacts
   
   В разделе "Main Class" выберите новый главный класс.

### 1.2. Создание Artifact
Перейдите в File -> Project Structure -> Artifacts.

Нажмите + -> JAR -> From modules with dependencies.

Выберите главный класс.

Включите опцию Include dependencies (если используются внешние библиотеки).

### 1.3. Сборка JAR-файла
Выберите Build -> Build Artifacts.

Нажмите Build для вашего артефакта.

Готовый JAR будет в папке out/artifacts/<Имя_проекта>_jar.

### 1.4. Jarfix
Если JAR файл не запускается по двойному щелчку левой кнопки мыши, нужно проверить установлено ли JRM.

Если не установлено, то установить JRM.

Если установлено, то скачать jarfix и запустить, данная программа создает ассоциацию между JAR и JRM, позволяя запускать такие файлы по двойному щелчку мыши. 

## 2 Создание установщика в Inno Setup
### 2.1. Установка Inno Setup
Скачайте Inno Setup с официального сайта.

Установите программу, следуя инструкциям мастера.

### 2.2. Создание скрипта установщика
Запустите Inno Setup и выберите Create a new script file using the Script Wizard.

Настройте все необходимые поля в Script Wizard и выберите в Application main executable file готовый JAR файл в папке out/artifacts/<Имя_проекта>_jar.

### 2.3 Компиляция установщика
Сохраните скрипт (.iss файл).

Нажмите Build -> Compile в Inno Setup Compiler для создания установщика.

Готовый .exe файл будет в папке Output.
