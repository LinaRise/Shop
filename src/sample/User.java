package sample;
/**
 * Alina Galeeva
 * 05.11.2018
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.AnimationAndDecor.AnimationAndDecor;

import java.io.*;
import java.util.Optional;


public class User {
    private String login;
    private String password;


    //создаем констркутор
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    //метод проверяющий есть введнный логин и пароль в файле, возвращает true или false
    public boolean checkData() {

        String path ="logPass.txt";
        boolean flag = false;
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);       //чтение файла
            BufferedReader bread = new BufferedReader(reader);
            String line = bread.readLine();
            while (line != null) {
                String[] arrSplit = line.split(" ");
                if (arrSplit[0].equals(login)) {           //проверка на соответствие
                    if (arrSplit[1].equals(password)) {
                        flag = true;
                        break;                 //выход из цикла
                    }
                }
                line = bread.readLine();

            }
        } catch (IOException e) {
            System.out.println("Файл не найден "+e);
            e.printStackTrace();
        }
        System.out.println(flag);
        return flag;           //возврат true или false
    }


    //метод проверки введнного логина при рестрации, возвращает true или false
    public boolean checkRegistartionLogin() {
        String path="logPass.txt";
        boolean flag = true;
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bread = new BufferedReader(reader);
            String line = bread.readLine();
            while (line != null) {
                String[] arrSplit = line.split(" ");
                if (arrSplit[0].equals(login))  flag = false;
                line = bread.readLine();
                flag = flag & true;
            }

        } catch (IOException e) {
            System.out.println("Файл logPass.txt не найден "+e);
            e.printStackTrace();
        }
        System.out.println(flag);
        return flag;
    }

//метод
    public void registrate() throws IOException {
        String path="logPass.txt";
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append("\r\n");
            String text = login + " " + password;
            writer.write(text);
            writer.flush();//чтобы данные пошли в файл сразу
        }

    }

}
