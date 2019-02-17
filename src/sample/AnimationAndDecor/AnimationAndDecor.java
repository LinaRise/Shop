package sample.AnimationAndDecor;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Decor {

   //метод для добавления своей иконки в сцену
    public static void addIcon(Stage stage, String pathToIcon){
        stage.getIcons().add(new Image(pathToIcon));
    }
}
