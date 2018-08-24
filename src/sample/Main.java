package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load (getClass ().getResource ("/sample/resources/fxml/main.fxml"));
        primaryStage.setTitle ("Dungeon Hero");
        Scene scene = new Scene (root, 350, 350);
        scene.getStylesheets ().add ("/css/menu.css");
        primaryStage.setScene (scene);
        primaryStage.getIcons ().add (new Image ("/sample/resources/pictures/icon.png"));
        primaryStage.setResizable (false);
        primaryStage.show ();
    }


    public static void main (String[] args) {
        launch (args);
    }
}

