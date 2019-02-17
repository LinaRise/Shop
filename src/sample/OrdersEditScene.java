import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Order;
import sample.addtionalClasses.Material;

import java.io.IOException;


public class OrdersEditScene {
    @FXML
    private ComboBox stateBox;

    @FXML
    private TextArea orderInfo;

    @FXML
    private TextArea measures;

    @FXML
    private TextArea telephone;

    @FXML
    private TextArea cost;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;


    private Stage orderEditStage;
    private Order order;
    private boolean okClicked=false;

//устанвока сцены дял окна

    public void setOrderEditStage(Stage orderEditStage) {
        this.orderEditStage = orderEditStage;
    }

    //присваиваем  полям значения объекта класса
    public void setOrder(Order order) {
        this.order = order;
       stateBox.setValue(order.getState());
       measures.setText(order.getMeasures());
       orderInfo.setText(order.getOrder());
        telephone.setText(order.getOrder());
        cost.setText(order.getCost());
    }

    //метод, который возвращает true, если данные, введенные в текстовые поля, корректные
    private boolean isInputValid() {
        String errorMessage = "";

        if (measures.getText() == null || measures.getText().length() == 0 ) {
            errorMessage += "Нет доступных мерок!\n";
        }
        if (orderInfo.getText() == null || orderInfo.getText().length() == 0 ) {
            errorMessage += "Нет доступного описания заказа!\n";
        }
        if (telephone.getText() == null || telephone.getText().length() == 0 || !telephone.getText().matches("\\d+")) {
            errorMessage += "Нет доступного способа связи!\n";
        }
        if (cost.getText() == null || cost.getText().length() == 0 || !cost.getText().matches("\\d+")) {
            errorMessage += "Не указана стоимость!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(orderEditStage);
            alert.setTitle("Ошибка ");
            alert.setHeaderText("Пожалуйста, внесите корректную информацию о заказе");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

    }



    @FXML
    private void handleOk() {
        if (isInputValid()) {
            order.setState(stateBox.getValue().toString());
            order.setOrder(orderInfo.getText());
            order.setMeasures(measures.getText());
            order.setTelephone(telephone.getText());
            order.setCost(cost.getText());
            Order.addOrder(order);

            okClicked = true;
            orderEditStage.close();
        }
    }

        @FXML
        private void handleCancel() {
            orderEditStage.close();
        }
    }

}
