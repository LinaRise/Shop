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
import sample.AnimationAndDecor.AnimationAndDecor;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    @FXML
    private TextField loginField;

    @Override
    public void start(Stage stage) throws Exception{
       LoginSceneController logscene=new LoginSceneController();
       logscene.loginSceneCall();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
