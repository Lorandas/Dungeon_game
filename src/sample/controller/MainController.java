/**Класс представляет собой контроллер fxml файла endGame.fxml
 * В этом классе проинициализированный метод initialize интерфейса Initializable
 * Проинициализированны кнопки приложения:
 * "Играть" - actionPlay который вызывает файл game.fxml и контроллер этого файла GameController.java
 * "Выход" - actionQuit который закрывает приложение
 * @autor Yaroslav Martynov
 * @version 1.0
 */


package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    public Label textLabel;

    public Button btnPlay;
    public Button btnQuit;

    private static Stage stage;

    public static Stage getStage () {
        return stage;
    }

    public static void setStage (Stage stage) {
        MainController.stage = stage;
    }

    @Override
    public void initialize (URL location, ResourceBundle resources) {

    }

    public void actionPlay (ActionEvent event) {
        try {
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resources/fxml/game.fxml"));
            GameController controller = loader.getController ();
            Parent root = loader.load();
            stage.setTitle("Dungeon Hero");
            stage.setMinHeight(640);
            stage.setMinWidth(640);
            stage.setResizable(false);
            Scene scene = new Scene (root, 640, 640);
            scene.getStylesheets ().add ("/sample/resources/css/game.css");
            stage.getIcons ().add (new Image ("/sample/resources/pictures/icon.png"));
            stage.setScene(scene);
            stage.show();
            GameController.setController (controller);
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) btnPlay.getScene().getWindow();
        stage.close();
    }

    public void actionQuit (ActionEvent event) {
        Platform.exit ();
    }
}
