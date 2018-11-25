package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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


    public TextField getLoginField() {
        return loginField;
    }

    public void setLoginField(TextField loginField) {
        this.loginField = loginField;
    }

    //метод выводящий сцену Входа
    void loginSceneCall(){
        Stage stage = new Stage();
        stage.setTitle("Вход в систему");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/loginScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            scene.getStylesheets().add(0, "sample/styles/loginScene.css");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл  loginScene.fxml не найден " );
            e.printStackTrace();
        }LoginSceneController.toClose(stage);
    }


    static void toClose(Stage stage){
        stage.setOnCloseRequest((WindowEvent regEx) -> {
            regEx.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход");
            alert.setHeaderText("Нажав ОК Вы выйдете из приложения");
            alert.setContentText("Вы уверены?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();

            }
        });
    }


    /*вход в приложение, сверка введенных данных
    если все верно выводит сцену Основного меню,
    иначе выводит сообщение об ошибке
     */
    private boolean signingIn() {
        System.out.print("вы нажали кнопку войти");
        boolean isMainSceneOpen=false;
        User a = new User(loginField.getText(), passwordField.getText());
        if (a.checkData() == true) {
            Stage stage=(Stage) buttonLogin.getScene().getWindow();
            stage.close();
            setLoginField(loginField);
            //создаем новую сцену с выбором дальнейшего действия
            MainMenuSceneController mainMenu=new MainMenuSceneController();
            mainMenu.mainMenuSceneCall();
            isMainSceneOpen=true;

        } else {
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
        linkRegistrate.getScene().getWindow().hide();


    }






    @FXML
    void initialize() {
        buttonCancel.setOnAction(event -> {  //очистка полей логина и пароля при нажатии на кнопку "Отмена"
            loginField.clear();
            passwordField.clear();
        });


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

