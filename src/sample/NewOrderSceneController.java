package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.AnimationAndDecor.AnimationAndDecor;

import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;

public class NewOrderSceneController {
    private ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private TextField fioField;

    @FXML
    private TextArea orderField;

    @FXML
    private TextField measuresField;
    @FXML
    private ComboBox stateBox;


    @FXML
    private TextField costField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    @FXML
    private TextField telephoneField;

    //метод выводящий сцену создания нового заказа
    void newOrderSceneCall(){
        Stage stage = new Stage();
        stage.setTitle("");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/newOrderScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            AnimationAndDecor.addIcon(stage, "sample/assets/needle.png");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл mainMenuScene.fxml не найден " );
            e.printStackTrace();
        }Main.toClose(stage, "Нажав ОК Вы прекратите создание заказа ");
        }


    public boolean ifInputIsValid (){
        String errorMessage = "";

        if (fioField.getText() == null || fioField.getText().length() == 0) {
            errorMessage += "Нет доступного имени заказчика!\n";
        }
        if (telephoneField.getText() == null || telephoneField.getText().length() == 0){
            errorMessage += "Нет доступного телефона заказчика!\n";
        }
        if (orderField.getText() == null || orderField.getText().length() == 0) {
            errorMessage += "Нет достпного описания заказа!\n";
        }

        if (measuresField.getText() == null || measuresField.getText().length() == 0) {
            errorMessage += "Не введены необходиме измерения!\n";
        }
        if (stateBox.getValue().toString() == null || stateBox.getValue().toString().length() == 0) {
            errorMessage += "Не введен  статус заказа!\n";
        }
        if (costField.getText() == null || measuresField.getText().length() == 0) {
            errorMessage += "Не введена сумма заказа!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Пожалуйста, внесите корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void saveButtonClick() {

        if (ifInputIsValid()) {
            Order newOrder = new Order(fioField.getText(), telephoneField.getText(), orderField.getText(),
                    measuresField.getText(), stateBox.getValue().toString(), costField.getText());
            newOrder.addNewOrder();
            clearFields();
            if (Main.toClose((Stage) saveButton.getScene().getWindow(), "Нажав Ок вы прекратите создание заказа")) {
                MainMenuSceneController scene = new MainMenuSceneController();
                scene.mainMenuSceneCall();
            }
        }
    }






    //метод для ввода в тектовое поле только цифр и звуковой сигнал при наборе неразрешенных

    public void phoneTyped(javafx.scene.input.KeyEvent keyEvent) {
        String key = keyEvent.getCharacter();
        System.out.println(key+"1");
        if((!key.isEmpty() && !Character.isDigit(key.charAt(0)))){
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                keyEvent.consume();
        }
    }

    private void clearFields(){
        fioField.clear();
        telephoneField.clear();
        orderField.clear();
        measuresField.clear();
        stateBox.setValue("");
        costField.clear();
    }
//|| keyEvent.getCode() == KeyCode.BACK_SPACE || keyEvent.getCode() == KeyCode.DELETE))



    @FXML
    void initialize(){
        saveButton.setOnAction(event -> {
            saveButtonClick();
        });

        cancelButton.setOnAction(event -> {
            clearFields();
        });

        list.add("Оплачено");
        list.add("Выполнено");
        list.add("Выдано клиенту");
        stateBox.setItems(list);
        stateBox.setValue("");

    }





}
