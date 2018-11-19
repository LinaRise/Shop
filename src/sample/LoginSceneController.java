package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.addtionalClasses.User;


import java.io.IOException;
import java.util.Optional;

public class LoginSceneController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancel;
    @FXML
    private Hyperlink linkRegistrate;


//метод выводящий сцену Входа
    void loginSceneCall(){
        Stage stage = new Stage();
        stage.setTitle("Основное меню");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/loginScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
           // scene.getStylesheets().add(0, "sample/styles/loginScene.css");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл  loginScene.fxml не найден " );
            e.printStackTrace();
        }Main.toClose(stage,"Нажав ОК Вы выйдете из приложения");
    }

    /*вход в приложение, сверка введенных данных
    если все верно выводит сцену Основного меню,
    иначе выводит сообщение об ошибке
     */
    private boolean signingIn()  {
        System.out.print("вы нажали кнопку войти");
        boolean isMainSceneOpen=false;//проверка открываеть ли доступ к основному меню
        User a = new User(loginField.getText(), passwordField.getText());
        boolean isAdmin=false;
        try {
             isAdmin=a.isAdmin("adminPass.txt");
        } catch (IOException e) { System.out.println("Файл adminPass.txt не найден при вызове метода isAdmin в LoginSceneController SigningIn");
            e.printStackTrace();
        }
        if (a.checkData() == true && !isAdmin) {
            Stage stage=(Stage) buttonLogin.getScene().getWindow();
            stage.close();
            //создаем новую сцену основног меню для работника с выбором дальнейшего действия
            MainMenuSceneController mainMenu=new MainMenuSceneController();
            mainMenu.mainMenuSceneCall();
            isMainSceneOpen=true;

        } else if( isAdmin){
            //здесь открывается сцена доступная только админстратору!!!!!
            System.out.println("Сцена администратора");

        }


        else{
            System.out.println("Данные не совпали");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Введенные данные не совпали");
            alert.setContentText("Пожалуйста, проверьте введенные логин и пароль");
            alert.showAndWait();
        }

return  isMainSceneOpen;
    }

/*
метод который открывает сцену регистрации при нажатии на ссылку "Зарегистрироваться"
*/

    private void registrationIn() {
        Stage stage = (Stage) linkRegistrate.getScene().getWindow();
        stage.close();
        RegistrationSceneController regScene = new RegistrationSceneController();
        regScene.registrationSceneCall();
    }






    @FXML
    void initialize() {

        buttonCancel.setOnAction(event -> {  //очистка полей логина и пароля при нажатии на кнопку "Отмена"
            loginField.clear();
            passwordField.clear();
        });

/*вызова метода входа в приложение(проверка данных и открытие сцены)
 при нажатии на кнопку "Вход" или клавиши Enter,
 при нажатии на клавиши вверх/вниз фокус будет премещаться м/у
 полем для логина и пароля, также если метод возвращает true-то мы закрываем окно Входа,
 тк открывается окно Оснвоног меню
 */
        buttonLogin.setOnAction(event ->{
        if (signingIn()) {
            Stage loginStage = (Stage) buttonLogin.getScene().getWindow();
            loginStage.close();
        }
        });

        loginField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
               if( signingIn()){
                    Stage loginStage = (Stage) buttonLogin.getScene().getWindow();
                    loginStage.close();
                }
            }
            if (event.getCode().equals(KeyCode.DOWN)) {
                passwordField.requestFocus();
            }

        });

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                signingIn();
            }
            if (event.getCode().equals(KeyCode.UP)) {
                loginField.requestFocus();
            }

        });

//вызов метода для регистрации в приложении
        linkRegistrate.setOnAction(event -> registrationIn());
    }
}

