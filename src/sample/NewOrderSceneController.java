package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.AnimationAndDecor.AnimationAndDecor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.awt.*;
import java.io.*;
import java.util.Optional;

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
            scene.getStylesheets().add(0, "sample/styles/newOrderScene.css");
            AnimationAndDecor.addIcon(stage, "sample/assets/needle.png");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл mainMenuScene.fxml не найден " );
            e.printStackTrace();
        }NewOrderSceneController.toClose(stage);

        }


    static void toClose(Stage stage){
        stage.setOnCloseRequest((WindowEvent regEx) -> {
            regEx.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход");
            alert.setHeaderText("Нажав ОК Вы прекратите создание заказа");
            alert.setContentText("Вы уверены?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                MainMenuSceneController scene=new MainMenuSceneController();
                scene.mainMenuSceneCall();
                stage.close();

            }
        });
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
            String loginOfWorker=null;
            try {
                BufferedReader reader=new BufferedReader(new FileReader("temp.txt"));
                loginOfWorker=reader.readLine();
                System.out.println(loginOfWorker);
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Order newOrder = null;
            try {
                System.out.println( Order.orderNum());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                newOrder = new Order(Order.orderNum(),fioField.getText(), telephoneField.getText(), orderField.getText(),
                        measuresField.getText(), stateBox.getValue().toString(), costField.getText(),loginOfWorker);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newOrder.addNewOrder();
            clearFields();

        }
    }






    //метод для ввода в  поле телефона  только цифр и звуковой сигнал при наборе неразрешенных
    public void phoneTyped(javafx.scene.input.KeyEvent keyEvent) {
        final int maxCharacter=11;
        String key = keyEvent.getCharacter();
        if((!key.isEmpty() && !Character.isDigit(key.charAt(0))|| (telephoneField.getText().length()>=maxCharacter) )){
            Toolkit tk = Toolkit.getDefaultToolkit();
            tk.beep();
            keyEvent.consume();
        }
    }

    //метод для ввода в поле фио только букв и звуковой сигнал при наборе неразрешенного
    public void fioTyped(javafx.scene.input.KeyEvent keyEvent) {
        final int maxCharacter=100;
        String key = keyEvent.getCharacter();

            if ((!key.isEmpty() && Character.isDigit(key.charAt(0)) || (fioField.getText().length() >= maxCharacter))) {
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                keyEvent.consume();
            }
        }


    //метод для ввода в поле суммы только цифр и звуковой сигнал при наборе неразрешенного
    public void costTyped(javafx.scene.input.KeyEvent keyEvent) {
        final int maxCharacter=20;

        String key = keyEvent.getCharacter();

        if ((!key.isEmpty() && (!Character.isDigit(key.charAt(0))) || (costField.getText().length() >= maxCharacter))) {
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
