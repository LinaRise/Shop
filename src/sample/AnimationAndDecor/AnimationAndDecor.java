package sample;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AnimationAndDecor {

   //метод для добавления своей иконки в сцену
    static void addIcon(Stage stage,String pathToIcon){
        stage.getIcons().add(new Image(pathToIcon));
    }
}
