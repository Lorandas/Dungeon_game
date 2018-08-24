/**Класс представляет собой контроллер fxml файла endGame.fxml
 * В этом классе проинициализированный метод initialize интерфейса Initializable
 * Проинициализированны кнопки приложения:
 * "Новая игра" - actionNewGame который вызывает файл game.fxml и контроллер этого файла GameController.java
 * "Главное меню" - actionMainMenu который вызывает main.fxml и контролер этого файла MenuController.java
 * "Выход" - actionExit который закрывает приложение
 * Так же присутсвует статический вызов данного класса
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
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.gameLogic.links.GameObjects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {
    public ImageView imageBackground;

    public Button btnNewGame;

    public Button btnMainMenu;
    
    public Button btnExit;

    public Label textLabel;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        imageBackground.setImage (GameObjects.END_GAME_GOLDEN_CHEST);
    }

    public void actionNewGame (ActionEvent event) {
        GameController.getStage ().close ();
        try {
            MainController.setStage ( new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resources/fxml/game.fxml"));
            GameController controller = loader.getController ();
            Parent root = loader.load();
            MainController.getStage ().setTitle("Dungeon Hero");
            MainController.getStage ().setMinHeight(640);
            MainController.getStage ().setMinWidth(640);
            MainController.getStage ().setResizable(false);
            Scene scene = new Scene (root, 640, 640);
            scene.getStylesheets ().add ("/sample/resources/css/game.css");
            MainController.getStage ().getIcons ().add (new Image ("/sample/resources/pictures/icon.png"));
            MainController.getStage ().setScene(scene);
            MainController.getStage ().show();
            GameController.setController (controller);
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        stage.close ();
    }

    public void actionMainMenu (ActionEvent event) {
        try {
            GameController.getStage ().close ();
            Stage stage = new Stage ();
            FXMLLoader loader = new FXMLLoader (EndGameController.class.getResource ("/sample/resources/fxml/main.fxml"));
            Parent root = loader.load ();
            stage.setTitle ("Dungeon Hero");
            stage.setMinHeight (350);
            stage.setMinWidth (350);
            stage.setResizable (false);
            Scene scene = new Scene (root, 350, 350);
            scene.getStylesheets ().add ("/sample/resources/css/menu.css");
            stage.getIcons ().add (new Image ("/sample/resources/pictures/icon.png"));
            stage.setScene (scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.close ();
    }

    public void actionExit (ActionEvent event) {
        Platform.exit ();
    }

    public static final void actionEndGame(){
        try {
            Stage stage = new Stage ();
            stage.initModality (Modality.WINDOW_MODAL);
            FXMLLoader loader = new FXMLLoader (EndGameController.class.getResource ("/sample/resources/fxml/endGame.fxml"));
            Parent root = loader.load ();
            stage.setTitle ("Dungeon Hero");
            stage.setMinHeight (350);
            stage.setMinWidth (350);
            stage.setResizable (false);
            Scene scene = new Scene (root, 350, 350);
            scene.getStylesheets ().add ("/sample/resources/css/endGame.css");
            stage.getIcons ().add (new Image ("/sample/resources/pictures/icon.png"));
            stage.initOwner (MainController.getStage ());
            stage.setOnCloseRequest (e -> Platform.exit ());
            stage.setScene (scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
