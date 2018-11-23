package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.AnimationAndDecor.AnimationAndDecor;


import java.io.IOException;

public class MainMenuSceneController {

    @FXML
    private ImageView materialsImg;

    @FXML
    private Button materialsButton;

    @FXML
    private ImageView newOrderImg;

    @FXML
    private Button newOrderButton;

    @FXML
    private ImageView ordersListImg;

    @FXML
    private Button ordersListButton;






    //метод выводящий сцену Входа
    void mainMenuSceneCall(){
        Stage stage = new Stage();
        stage.setTitle("Основное меню");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/mainMenuScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            AnimationAndDecor.addIcon(stage, "sample/assets/needle.png");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл mainMenuScene.fxml не найден " );
            e.printStackTrace();
        }Main.toClose(stage, "Нажав ОК Вы выйдете из основного меню ");

        }




        @FXML
    void initialize(){
        newOrderButton.requestFocus();

        materialsButton.setOnAction(event -> {
            MaterialsSceneWorkerController matScene=new MaterialsSceneWorkerController();
            matScene.materialsWorkerSceneCall();
            materialsButton.getScene().getWindow().hide();


        });
            materialsImg.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                MaterialsSceneWorkerController matScene=new MaterialsSceneWorkerController();
                matScene.materialsWorkerSceneCall();
                materialsImg.getScene().getWindow().hide();
               event.consume();
            });


            newOrderButton.setOnAction(event -> {
                NewOrderSceneController newOrder=new NewOrderSceneController();
                newOrder.newOrderSceneCall();
                newOrderButton.getScene().getWindow().hide();

            });

            newOrderImg.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                NewOrderSceneController newOrder=new NewOrderSceneController();
                newOrder.newOrderSceneCall();
                newOrderImg.getScene().getWindow().hide();
                event.consume();
            });

            ordersListButton.setOnAction(event -> {
                OrdersCatSceneW orderScene=new OrdersCatSceneW();
                orderScene.orderCatSceneCall();
                ordersListButton.getScene().getWindow().hide();

            });

           ordersListImg.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
               OrdersCatSceneW orderScene=new OrdersCatSceneW();
               orderScene.orderCatSceneCall();
               ordersListButton.getScene().getWindow().hide();
               event.consume();

            });
        }

    }



