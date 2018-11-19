package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.addtionalClasses.Material;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Arrays;

public class MaterialsSceneWorkerController {
    private ObservableList<Material> materialData = FXCollections.observableArrayList();
    @FXML
    private TableView<Material> matTable;

    @FXML
    private TableColumn<Material, String> matColumn;

    @FXML
    private TableColumn<Material, String> colorColumn;

    @FXML
    private TableColumn<Material, String> amountColumn;

    @FXML
    private TableColumn<Material, String> extraColumn;



    @FXML
    private Label matLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label extraLabel;

    @FXML
    private ImageView materialImg;
    @FXML
    private Button addInfoButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button chooser;


    //метод вызова сцены с каталогом материлов для работников
    void materialsWorkerSceneCall(){
        Stage stage = new Stage();
        stage.setTitle("Каталог материалов");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/matCatalogSceneW.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
           // stage.setResizable(false);
            scene.getStylesheets().add(0, "sample/styles/materialsCatalogScene.css");
            stage.show();
        } catch (IOException e) {
            System.out.println("Файл  matCatalogSceneW.fxml не найден " );
            e.printStackTrace();
        }Main.toClose(stage,"Нажав ОК Вы выйдете из каталога материалов");
    }

//метод для заполнения таблицы материалов из файла
    private void matTableFillIn(String path){
         path = "material.txt";
         File file = new File(path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл material.txt не найден при вызове метода matTableFillIn " + e);
            e.printStackTrace();
        }
        BufferedReader bread = new BufferedReader(reader);
        String line = null;
        try {
            line = bread.readLine();
        } catch (IOException e) {
            System.out.println("Файл material.txt не найден при вызове метода matTableFillIn " + e);
            e.printStackTrace();
        }//сплит сторк и заполнение
        while (line != null) {
            String[] arrSplit = line.split("/");
            System.out.println(Arrays.toString(arrSplit));
            materialData.add(new Material(arrSplit[0],arrSplit[1],arrSplit[2],arrSplit[3]));
            matColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
            colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
            extraColumn.setCellValueFactory(new PropertyValueFactory<>("extra"));
           matTable.setItems(materialData);
            try {
                line = bread.readLine();
            } catch (IOException e) {
                System.out.println("Файл material.txt пуст или не найден "+e);
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Файл material.txt пуст или не найден "+e);
            e.printStackTrace();
        }
    }



//метод для отображения подробной информации
    private void showMaterialDetails(Material material) {

        if (material != null) {
           matLabel.setText(material.getMaterial());
            colorLabel.setText(material.getColor());
            amountLabel.setText(material.getAmount());
           extraLabel.setText(material.getExtra());
        } else {
            matLabel.setText(null);
            colorLabel.setText(null);
            amountLabel.setText(null);
            extraLabel.setText(null);
        }
    }
    //метод, вызывающий сцену редактирования для работника
    public boolean showMaterialEditWDialog(Material material) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditWMaterialSceneController.class.getResource("fxml/editWMaterial.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage editWMStage = new Stage();
            editWMStage.setTitle("Редактировать продукты");
            editWMStage.initModality(Modality.WINDOW_MODAL);
            editWMStage.initOwner(null);
            Scene scene = new Scene(page);
            editWMStage.setScene(scene);

            // Передаём адресата в контроллер.
            EditWMaterialSceneController controller = loader.getController();
            controller.setEditWMStage(editWMStage);
            controller.setMaterial(material);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            editWMStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println("Ошибка вывода диалоговой сцены на экран" + e);
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void editWMaterial() {
        System.out.println("вы нажади кнопку изменить");
        Material selectedMaterial = matTable.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            boolean okClicked = showMaterialEditWDialog(selectedMaterial);
            if (okClicked) {
                showMaterialDetails(selectedMaterial);
                int selectedIndex = matTable.getSelectionModel().getSelectedIndex();
               materialData.set(selectedIndex,selectedMaterial);
                try {
                    Material.rewriteMaterialCatalog(matTable.getItems(),"materials.txt");
                } catch (IOException e) {
                    System.out.println("Файл materials.txt не найден при вызове метода EditWMaterial "+e);
                    e.printStackTrace();
                }

            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Ни один материал не выбран");
            alert.setContentText("Пожалйуста, выберите проект");

            alert.showAndWait();
        }
    }


    @FXML
    void initialize(){
        matTableFillIn("material.txt");
        matTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> showMaterialDetails(newValue));
        addInfoButton.setOnAction(event-> {
            editWMaterial();
           matTable.getSelectionModel().clearSelection();
        });



        chooser.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("D:\\Alina\\проги на Kotlin\\Atelier2\\src\\sample\\assets"));
            fc .getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"));
            fc.setTitle("Open Resource File");

            File selectedFile=fc.showOpenDialog(null);


        });




    }
}
