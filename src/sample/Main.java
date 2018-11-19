package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    @FXML
    private TextField loginField;

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("fxml/loginScene.fxml"));
        primaryStage.setTitle("Ателье");
        Scene scene=new Scene(root,800,460);
        scene.getStylesheets().add(0, "sample/styles/loginScene.css");
       primaryStage.setResizable(false);
       primaryStage.setScene(scene);
        primaryStage.show();
        toClose(primaryStage,"входа");


    }

    static void toClose(Stage stage,String text){

        stage.setOnCloseRequest((WindowEvent regEx) -> {
        regEx.consume();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setHeaderText(text);
        alert.setContentText("Вы уверены?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            stage.close();
        }
    });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
