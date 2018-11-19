package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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

            stage.show();
        } catch (IOException e) {
            System.out.println("Файл mainMenuScene.fxml не найден " );
            e.printStackTrace();
        }Main.toClose(stage, "Нажав ОК Вы выйдете из основного меню ");
        }



        @FXML
    void initialize(){

        materialsButton.setOnAction(event -> {
            Stage mainMenuStage = (Stage) materialsButton.getScene().getWindow();
            mainMenuStage.close();
            MaterialsSceneWorkerController matScene=new MaterialsSceneWorkerController();
            matScene.materialsWorkerSceneCall();
        });
            materialsImg.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Stage mainMenuStage = (Stage) materialsButton.getScene().getWindow();
                mainMenuStage.close();
                MaterialsSceneWorkerController matScene=new MaterialsSceneWorkerController();
                matScene.materialsWorkerSceneCall();
                event.consume();
            });

        }

    }



