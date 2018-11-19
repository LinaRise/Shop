package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.addtionalClasses.Material;

import java.io.IOException;

public class EditWMaterialSceneController{
    @FXML
    private TextField matField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField extraField;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonCancel;

    private Stage editWMStage;
    private Material material;
    private boolean okClicked=false;


    //запрет на изменение некоторых полей в  редкатрованиии для работников
    private static  void restrictEdit(TextField textField){
        textField.setEditable(false);
        textField.setFocusTraversable(false);
        textField.setMouseTransparent(true);

    }

//устанавливаем новую сцену для этого окна в контроллере
    public void setEditWMStage(Stage editWMStage) {
        this.editWMStage = editWMStage;
       EditWMaterialSceneController.restrictEdit(matField);
        EditWMaterialSceneController.restrictEdit(colorField);
        EditWMaterialSceneController.restrictEdit(amountField);


    }
//присваиваем тесктовым полям значения объекта класса
    public void setMaterial(Material material) {
        this.material = material;
        matField.setText(material.getMaterial());
        colorField.setText(material.getColor());
       amountField.setText(material.getAmount());
        extraField.setText(material.getExtra());
    }

//метод, возвращающий true, если пользователь кликнул OK, в другом случае false
    public boolean isOkClicked() {
        return okClicked;
    }


    //метод, который возвращает true, если данные, введенные в текстовые поля, корректные
    private boolean isInputValid() {
        String errorMessage = "";

        if (extraField.getText() == null || extraField.getText().length() == 0 ) {
            errorMessage += "Нет доступного количества!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editWMStage);
            alert.setTitle("Ошибка ");
            alert.setHeaderText("Пожалуйста, внесите корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
           material.setMaterial(matField.getText());
            material.setColor(colorField.getText());
            material.setAmount(amountField.getText());
            material.setExtra(extraField.getText());
            try {
                Material.addMaterial(material,"material.txt");
            } catch (IOException e) {
                System.out.println("Файл material.txt не найден при вызове метода handleOk"+e);
                e.printStackTrace();
            }

            okClicked = true;
            editWMStage.close();
        }
    }
//метод, закрывающий сцену редактирования материала для работника
    @FXML
    private void handleCancel() {
        editWMStage.close();
    }

    @FXML
    void initialize() {
        buttonOk.setOnAction(event -> handleOk());
        buttonCancel.setOnAction(event -> handleCancel());
    }
}
