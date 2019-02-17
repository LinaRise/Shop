package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.AnimationAndDecor.AnimationAndDecor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class OrdersCatSceneW {


    private ObservableList<Order> orderData = FXCollections.observableArrayList();
    private ObservableList<Order> orderDetailData = FXCollections.observableArrayList();
    private ObservableList<String> list = FXCollections.observableArrayList();
        @FXML
        private TableView<Order> ordersTab;

        @FXML
        private TableColumn<?, ?> orderNum;

        @FXML
        private TableColumn<?, ?> fio;

        @FXML
        private Label orderNumLabel;

        @FXML
        private ComboBox stateBox;

        @FXML
        private TableView<Order> orderDetailTab;

        @FXML
        private TableColumn<?, ?> measures;

    @FXML
    private TableColumn<?, ?> telephone;

    @FXML
    private TextArea orderTextArea;

    @FXML
        private Label costLabel;

    @FXML
    private ImageView searchImg;

    //метод выводящий сцену катлога заквзов
    void orderCatSceneCall(){
        Stage stage = new Stage();
        stage.setTitle("Основное меню");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/ordersCatSceneW.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(0, "sample/styles/ordersCatScene.css");
            AnimationAndDecor.addIcon(stage, "sample/assets/needle.png");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл ordersCatSceneW.fxml не найден " );
            e.printStackTrace();
        }OrdersCatSceneW.toClose(stage);
    }

    static void toClose(Stage stage){
        stage.setOnCloseRequest((WindowEvent regEx) -> {
            regEx.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход");
            alert.setHeaderText("Нажав Ок вы выйдите из каталога товаров");
            alert.setContentText("Вы уверены?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                MainMenuSceneController scene=new MainMenuSceneController();
                scene.mainMenuSceneCall();
                stage.close();

            }
        });
    }

    //метод для заполнения таблицы заказа из файла для конкретног работника
    private void orderTableFillIn() {

        ArrayList<String> lineArray = null;
        try {
            lineArray = Order.ordersFileread("Orders.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 1;

        for (String line : lineArray) {
            String[] arrSplit = line.split("\\|", 8);

            if (Order.loginOfWorker().equals(arrSplit[7])) {
                System.out.println(Arrays.toString(arrSplit));
                orderData.add(new Order(Integer.parseInt(arrSplit[0]), arrSplit[1], arrSplit[2], arrSplit[3], arrSplit[4], arrSplit[5], arrSplit[6]));
                fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
                orderNum.setCellValueFactory(new PropertyValueFactory<>("orderNum"));
                ordersTab.setItems(orderData);
                i++;
            }
        }
    }



    private void orderDetailTableFillIn(Order order){
        orderDetailTab.getItems().clear();
        if (order != null) {
            orderDetailData.add(new Order(order.getMeasures(),order.getTelephone()));
            measures.setCellValueFactory(new PropertyValueFactory<>("measures"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            orderDetailTab.setItems(orderDetailData);
            orderNumLabel.setText(String.valueOf(order.getOrderNum()));
            stateBox.setValue(order.getState());
            costLabel.setText(order.getCost());
            orderTextArea.setText(order.getOrder());

        }

    }







    @FXML
    void initialize(){
        list.add("Оплачено");
        list.add("Выполнено");
        list.add("Выдано клиенту");
        stateBox.setItems(list);
        stateBox.setValue("");
        orderTableFillIn();
        ordersTab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            orderDetailTableFillIn(newValue));
        }

}

