/**Класс представляет собой контроллер fxml файла endGame.fxml
 * В этом классе проинициализированный метод initialize интерфейса Initializable
 * Проинициализированны в методе prepareActionHandlers слушатель событий
 * при нажатии клавиши методом pressedAction и при отпускании клавиши releasedAction стрелочки на клавиатури отвечающие за передвижение,
 * а так же кнопка G отвечающая за смену пола персонажа.
 * Так же проинициализированны кнопки на панели инструментов:
 * "Новая игра" - actionNewGame который вызывает файл game.fxml и контроллер этого файла GameController.java
 * "Выход" - actionExit который закрывает приложение
 *
 * @autor Yaroslav Martynov
 * @version 1.0
 */


package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.gameLogic.level.GameLevel;
import sample.gameLogic.links.GameObjects;
import sample.gameLogic.objects.Player;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public Label countCoin;
    public static Label staticCountCoin;

    private static Stage stage;

    private static GameController controller;

    private HashSet<String> currentActiveKeys;
   // private HashMap<KeyCode, Boolean> keys;
    
    public MenuBar gameMenuBar;
    
    public Menu gameMenu;

    public MenuItem itemNewGame;
    public MenuItem itemExit;


    public AnchorPane holstPane;
    public static AnchorPane staticHolstPane;
    
    public ImageView imageLeft;
    public ImageView imageUp;
    public ImageView imageDown;
    public ImageView imageRigth;
    public ImageView imageGender;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        System.out.println ("init");
        staticCountCoin = countCoin;
        staticHolstPane = holstPane;
        stage = MainController.getStage ();
        prepareActionHandlers ();

        initHolst ();
    }

    public static Stage getStage () {
        return stage;
    }

    private void initHolst(){
        GameLevel.levelOne (holstPane);
    }

    public static void setController (GameController controller) {
        GameController.controller = controller;
    }

    public static GameController getController () {
        return controller;
    }

    private void prepareActionHandlers(){
        currentActiveKeys = new HashSet<String> ();
        stage.addEventFilter (KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent> () {
            @Override
            public void handle (KeyEvent event) {
                currentActiveKeys.add (event.getCode ().toString ());
                //System.out.println (currentActiveKeys);
                pressedAction ();
            }
        });
        stage.addEventFilter (KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent> () {
            @Override
            public void handle (KeyEvent event) {
                releasedAction ();
                currentActiveKeys.remove (event.getCode ().toString ());
            }
        });

    }

    private void pressedAction() {
        //System.out.println ("actions");
        if (currentActiveKeys.contains ("LEFT")) {
            imageRigth.setImage (new Image ("sample/resources/pictures/rigthWhite.png"));
            imageUp.setImage (new Image ("sample/resources/pictures/upWhite.png"));
            imageDown.setImage (new Image ("sample/resources/pictures/downWhite.png"));
            imageGender.setImage (new Image ("sample/resources/pictures/defaultG.png"));
            GameLevel.getPlayer ().getAnimation ().play ();
            GameLevel.getPlayer ().getAnimation ().setOffsetY (32);
            if(GameLevel.getPlayer ().moveX (-1))
                imageLeft.setImage (new Image ("sample/resources/pictures/leftRed.png"));
            else
                imageLeft.setImage (new Image ("sample/resources/pictures/leftGreen.png"));
        }
        else if (currentActiveKeys.contains ("RIGHT")) {
            imageLeft.setImage (new Image ("sample/resources/pictures/leftWhite.png"));
            imageUp.setImage (new Image ("sample/resources/pictures/upWhite.png"));
            imageDown.setImage (new Image ("sample/resources/pictures/downWhite.png"));
            imageGender.setImage (new Image ("sample/resources/pictures/defaultG.png"));
            GameLevel.getPlayer ().getAnimation ().play ();
            GameLevel.getPlayer ().getAnimation ().setOffsetY (64);
            if(GameLevel.getPlayer ().moveX (1))
                imageRigth.setImage (new Image ("sample/resources/pictures/rigthRed.png"));
            else
                imageRigth.setImage (new Image ("sample/resources/pictures/rigthGreen.png"));
        }
        else if (currentActiveKeys.contains ("UP")) {
            imageLeft.setImage (new Image ("sample/resources/pictures/leftWhite.png"));
            imageRigth.setImage (new Image ("sample/resources/pictures/rigthWhite.png"));
            imageDown.setImage (new Image ("sample/resources/pictures/downWhite.png"));
            imageGender.setImage (new Image ("sample/resources/pictures/defaultG.png"));
            GameLevel.getPlayer ().getAnimation ().play ();
            GameLevel.getPlayer ().getAnimation ().setOffsetY (96);
            if(GameLevel.getPlayer ().moveY (-1))
                imageUp.setImage (new Image ("sample/resources/pictures/upRed.png"));
            else
                imageUp.setImage (new Image ("sample/resources/pictures/upGreen.png"));
        }
        else if (currentActiveKeys.contains ("DOWN")) {
            imageLeft.setImage (new Image ("sample/resources/pictures/leftWhite.png"));
            imageRigth.setImage (new Image ("sample/resources/pictures/rigthWhite.png"));
            imageUp.setImage (new Image ("sample/resources/pictures/upWhite.png"));
            imageGender.setImage (new Image ("sample/resources/pictures/defaultG.png"));
            GameLevel.getPlayer ().getAnimation ().play ();
            GameLevel.getPlayer ().getAnimation ().setOffsetY (0);
            if(GameLevel.getPlayer ().moveY (1))
                imageDown.setImage (new Image ("sample/resources/pictures/downRed.png"));
            else
                imageDown.setImage (new Image ("sample/resources/pictures/downGreen.png"));
        }
        else if (currentActiveKeys.contains ("G")) {
            imageLeft.setImage (new Image ("sample/resources/pictures/leftWhite.png"));
            imageRigth.setImage (new Image ("sample/resources/pictures/rigthWhite.png"));
            imageUp.setImage (new Image ("sample/resources/pictures/upWhite.png"));
            imageDown.setImage (new Image ("sample/resources/pictures/downWhite.png"));
            imageGender.setImage (new Image ("sample/resources/pictures/pressedG.png"));
            if(GameLevel.getPlayer ().getGender ().equals ("male")){
                System.out.println ("female");
                GameLevel.getPlayer ().setGender ("female");
                GameLevel.getPlayer ().setNewAnimation (new ImageView(GameObjects.FEMALE));
            }
            else if(GameLevel.getPlayer ().getGender ().equals ("female")){
                System.out.println ("male");
                GameLevel.getPlayer ().setGender ("male");
                GameLevel.getPlayer ().setNewAnimation (new ImageView(GameObjects.MALE));
            }
        }
        else {
            imageLeft.setImage (new Image ("sample/resources/pictures/leftWhite.png"));
            imageRigth.setImage (new Image ("sample/resources/pictures/rigthWhite.png"));
            imageUp.setImage (new Image ("sample/resources/pictures/upWhite.png"));
            imageDown.setImage (new Image ("sample/resources/pictures/downWhite.png"));
            imageGender.setImage (new Image ("sample/resources/pictures/defaultG.png"));
        }
    }
    private void releasedAction(){
        if(currentActiveKeys.contains ("LEFT")) {
            imageLeft.setImage (new Image ("sample/resources/pictures/leftWhite.png"));
            GameLevel.getPlayer ().getAnimation ().stop ();
        }
        else if(currentActiveKeys.contains ("RIGHT")) {
            imageRigth.setImage (new Image ("sample/resources/pictures/rigthWhite.png"));
            GameLevel.getPlayer ().getAnimation ().stop ();
        }
        else if(currentActiveKeys.contains ("UP")) {
            imageUp.setImage (new Image ("sample/resources/pictures/upWhite.png"));
            GameLevel.getPlayer ().getAnimation ().stop ();
        }
        else if(currentActiveKeys.contains ("DOWN")) {
            imageDown.setImage (new Image ("sample/resources/pictures/downWhite.png"));
            GameLevel.getPlayer ().getAnimation ().stop ();
        }
        else if(currentActiveKeys.contains ("G"))
            imageGender.setImage (new Image ("sample/resources/pictures/defaultG.png"));
    }

    public void actionGameMenu (ActionEvent event) {}

    public void actionNewGame (ActionEvent event) {
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
        Stage stage = (Stage) gameMenuBar.getScene().getWindow();
        stage.close ();

    }

    public void actionExit (ActionEvent event) {
        Platform.exit ();
    }
}
